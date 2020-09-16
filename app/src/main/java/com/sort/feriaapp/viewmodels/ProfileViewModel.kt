package com.sort.feriaapp.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.minimals.InstagramMinimal
import com.sort.feriaapp.data.repositories.EventRepository
import com.sort.feriaapp.data.repositories.UserRepository

/*ViewModel for ProfileFragment*/
class ProfileViewModel @ViewModelInject constructor(private val eventRepository: EventRepository, private val userRepository: UserRepository): ViewModel(){

    /*Images URL*/
    private val images = listOf<String>(
        "https://lh3.googleusercontent.com/pw/ACtC-3cRhqqrkTbSmP1JBj2QHUuhhkzjBjQlYb24tqdkCfr4g8eJ6l6G4Mv8_n3Yydj1KNDw81xBPlbtjj9QlBwfo7iF5datIDz5H3C2FlDRjV9M6Q_lC1XqtOpz_3GOhvsmZHEsfDaQ0h3tkreEvcCRF65R=w1103-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3eMJTcZsAIyu90_n6ucStUSTcJoXXMpJ--5PO7ogvJWg_4w1_1wUKsKa0P7sl9enl0oTeT8le4MR6mXSzamToxCkXseK_5kNga1jeNQr0eWO2YEybY7iGgmBPXqbKFFxYhtSkngBa46v7aTtEtUMw07=w1103-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3cWfXZ3lmNumvoKvy2LZ_thaf0cYHolXeDJTmsrgMLdApXzzpwFE2vpti_qBzg5PAIqCl3wyKBLHatSWDecn-TEOGmRqZkhMpCxH_ShtcuQWVGrmOk5V4R8l3KEib-f4OA462UfASkUhJED9ljmIjCy=w1542-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3e3AYCC6EYxsF86QCKNiVCSDLXIA4JeH3Pp_RTJa28uHlVA6i34OMuMtlJGQmMce5yQJCiZM_e3KFOvIxxuCGNeM-RQxoMIY7jbvhxTeTUx2Irlp9I-loWNDBjQKCLQOo9XSPpQ6gsPSnExHQ6vBz8O=w1320-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3db1lC-kqkNts5yRunhUuimrgqoqgyMm5qvGbDO0HobecPzKRw2sSCO7ubU2yUnHduHEYawQNLk4LcuGtfxHpM3dAoTGDikkoVPajohcZ8pAG1BLPDVmG7mDeu6JHbDNs17QF8MK5QyjrFx51qZKRMJ=w1210-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3dci6HzEqog67bjB1yHL1HPYQMrmmaEsXBHIPgh1E0Lamb-2o0k0Sqd-MhTuhGv3qUZy1kXYflIWmteDCNMSZQvUKGpqPiK7D_jqB6hU0Heg4d_I81UACx2fDHK3FRIlC7YgzPJl7I5WeHQb5JCJ67W=w1333-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3ctajQU3rdCKzKCecrQSzL2IAsr4Gs28QlxLgDXVz2Rf9l-87W-BRdSHx0HIEmUGbctd_N5YkW1Vr6KLl_HGEeT-20EDcSXX-A19UAdajKg244aMkrq7tzmteYhRJw-cAsQIvFSzD0qdFzAhqZrgpI6=w1471-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3dDJxbPfzZVxMlaTCCgq9begaw4EXV_sGAK7q04Q26IckLljGXJwnk1ceVGt0KC5-FFoX8zQcQO5SDr6cdHe_fcpxytXefdALqWVOa7ThOhLObVP2JCija4mxoD6qJQTGxJdJFePr8UeNnOcR8dixo7=w1241-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3euaNQGgcPL9riD0I80l4z02i5U7ci2pbaHzXujlXxXYafNAav2j8VhfbctUtRawXHT23kyN6HhiYBP8MDaGtF17l1KEUYO5FI-ectTM0N1BVooa0u7Uszyh-VhsGsE2mgON-YGdFgic7vCOBmzz3PB=w1243-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3enfCUMmVb1_nvyPpaNJ9uCtyP7O6cmrK4AceJVZydgFBn36OZWw2LZudF9BF6z_kfGp9pCwGjDZrW5kiVGTXM0JswJ6C8T47f5XyFddH84K3gYUCipUSprLEJEVBNVTeX9ymnnishrJoZUzfmYvKtw=w1243-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3djpw6QaK_1S8JHv7oly-8Z9GEvou7J8W-nFepqCnazX0nGAAHQRwSRa67ATVv9EcwbIMxBY4EA59r_TQqrAFCGN1B9fXqdDb77_iOyaBOzCn4o3W74wK3kEAoFyWFXN2vgcgmjG-3Y2GRECiUgvog5=w1241-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3ebr4sOMp0VYWihFoP8vpdTi3WlEGunSlpFHTbYCPVB6NKjslWfY9TJvijBnTV7GR1i6xu6p1atAaQRvup6TOrcm-rSIVL5ZmF_hjQDj5hbOM_6_f6mpyDn0XfMLgpIzX2rW6I_DBT_MrhgV162RYbR=w1103-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3ebcicwPS8--3QVREkpUHdB0rC8CrixrBP6V55DJ4pG-clcSkC1T4xOCx2rR1OP6bmkt8oGpjpnSR1dvmlg7rz-pGwXBQPq2iEQmDuWMBtsMYqv7TTcB8U9TQEXYuJAEo7JdLp95jEDr71IOX2cpuFa=w1379-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3fdZXXqoLYNuk5SzBfSgfBDPcxIUU2KMFYW1BI6OJLHY3P6rA9_9lU_3JslJNhjmEWKj7BaZ_PAbg6Tsh1c_B7M5B-n8QyBlnWnskR_GEtamWbIyRVCOwD4Z4N_jSjl6AgpFKSHX5YA0kkNmWIsTYUk=w1486-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3fvDZfJFOCz4k6BmRv2gJFzuk7UOzSQKBoA1B_Jmqs8kzscmHFQFm714-43Iac67HPTy2dE8eiaDVlDjGg_TAjhK4u93_JhQ4vgnVUSnO1XyYeHmWvKcINiCc-rUDEozvLW_UCM5A8SFd4-hKvhsEru=w1241-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3f9Qi-QK-k7VA-3a0TYusg-ZseJeT9Sk3vvMe2ZAhTy5DgUvWe0XWbORP0aN5fQ3pYSrx3VNzKNycIaAN3MLAJnq1uOWfDvZN-5Us7jSu_29y4keOMyFai-LByjybd023YfRBa8qcYs_Q4oWoNmaRaR=w1103-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3cx35mDvC7ZVGs4aePzccknQV8lwy07VcuF-QB7oQxYV9f-GnYuJgOlxbg4JwGvcvGnjQ-ycbuov4xakYjR5yFIDfXZkzQRGRCmUPe4NtqjsLMJC8avX5A0BwUYIy5TYanaGY9M-v8Miim_TSTxybm8=w1248-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3fMP6vwEj3RDxmxUWzeP3VsCaXC0y4YhMoK7rfUSL7saf08NoO0hZG3YOoNEidS1Sxc5MnlfPrgN0n5LQA_dglx3MK0LbQGZJrGAJQ2UKeDVGJRKCrj_Cy2Mgest6vyl69oj2EOgYOE7eJtaOQKxMhy=w1470-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3e_-ah3NWF1FMzKoibXSNuRT3dbwEXQHheQWiox0QmVPh0tSJLx3nNj9vMLZ2XiSYt3HLAXos01LfnKjBukWytG1SrjSK4dmK4BCoF-VxMszN_kOhRtaNE9fuq-fknGzKv6j9tRRjAOe5tW41nQaWPa=w1103-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3cui1BpEKLjxqkoiE2cXMVCVsYdyIcSnAoTG-FvKvyjxCblwRavR-rLpd7joOfw2IAPBCW-4_XcLt74udt53CVFeMglOT4nii2V6W_LdFhX3yaop67XEyC740V1janTtRPPwV5IZ6-tlLD4hCbxY0AX=w1103-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3dTObavVrWe_tNt_oQl9Zlh7UJgd92qTAQ--p0u4a7TSGjW--ok-8fRmzTm32ayUzWHjc9HVwSxP1O4SZ1OIpGVa-VA7AOK1YtDxcS0ernPjjIgu9HbfyCCdSuoQD9VK-ytqURnqlCpLVkb39zwiO0V=w1032-h774-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3chxLIIC_x4KYUVAUG0QvkJcZSdmzk-lYZnnXmDjPHUMoGyToj1bnGuk_U52zmZbD0cEORznlKzMCdANS4AXLO2TExA6YrF3dXqrU5bqZ85gGSq-fRcP6VEFkcliCpP26ipazmuBZviTAK_L36vrJsD=w1103-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3eH7oryeFewjGTvAVJ3pPnNMgXXhPsgfYuK1kRwUZdirKGmHq0d8wN1e4iYkckfgNmov0JmPzRuABH3tgdzkOO3nEy7wCA8amNx0xCZymt4xuYb4j_15Y-2CxwIimUCY6TV3vjsJYH5yV86gry9Exla=w1241-h827-no",
        "https://lh3.googleusercontent.com/pw/ACtC-3fEVwmIGrINqGTemVOCLraUEPF_q2oFK__xjPB66bm-SvNoCYc7lmO1R7ndEcuIrs_vjpHnldjrgrwTQDxKCNyprpcA-gkdjteIX_n07tteyH5wa_U8JZX8BJkbtgYXGJfuotVN9QKj70l1cQsIIJkm=w1103-h827-no"
    )

    /*Images Descriptions*/
    private val descriptions = listOf<String>(
        "Levantamiento arquitectónico Museo Casa Blanca",
        "Levantamiento arquitectónico Museo Casa Blanca",
        "Refuerzo Escolar de Matemática Instituto Nacional José Damián Villacorta",
        "Refuerzo Escolar de Matemática Instituto Nacional José Damián Villacorta",
        "Propuesta de mejora en Planta Lechera ENA",
        "Diseño de línea de impelencia Cooperativa Los Almendros San Ildefonso San Vicente",
        "Diseño de línea de impelencia Cooperativa Los Almendros San Ildefonso San Vicente",
        "Levantamiento topográfico en Santa Ana",
        "Levantamiento topográfico en Arcatao",
        "Levantamiento topográfico en Arcatao",
        "Levantamiento topográfico en Arcatao",
        "Diseño de Ciclo Ruta Puerto de La Libertad",
        "Entrega Levantamiento topográfico Arcatao",
        "Entrega Levantamiento arquitectónico Museo Casa Blanca",
        "Propuesta Arquitectónica Casa Comunal Santa Rosa, San Vicente",
        "Propuesta Arquitectónica Casa Comunal Santa Rosa, San Vicente",
        "Propuesta Arquitectónica Casa Comunal Santa Rosa, San Vicente",
        "Propuesta de mejora en Planta Lechera ENA",
        "Entrega de auditoría eléctrica Cuerpo de Bomberos",
        "Diagnóstico de la Diócesis de Chalatenango",
        "Diagnóstico Chalatenango",
        "Levantamiento Arq. Tin Marín",
        "Centro de Servicio Social",
        "Servicio Social"
    )

    val data = listOf<InstagramMinimal>(
        InstagramMinimal(images[0], descriptions[0]),
        InstagramMinimal(images[1], descriptions[1]),
        InstagramMinimal(images[2], descriptions[2]),
        InstagramMinimal(images[3], descriptions[3]),
        InstagramMinimal(images[4], descriptions[4]),
        InstagramMinimal(images[5], descriptions[5]),
        InstagramMinimal(images[6], descriptions[6]),
        InstagramMinimal(images[7], descriptions[7]),
        InstagramMinimal(images[8], descriptions[8]),
        InstagramMinimal(images[9], descriptions[9]),
        InstagramMinimal(images[10], descriptions[10]),
        InstagramMinimal(images[11], descriptions[11]),
        InstagramMinimal(images[12], descriptions[12]),
        InstagramMinimal(images[13], descriptions[13]),
        InstagramMinimal(images[14], descriptions[14]),
        InstagramMinimal(images[15], descriptions[15]),
        InstagramMinimal(images[16], descriptions[16]),
        InstagramMinimal(images[17], descriptions[17]),
        InstagramMinimal(images[18], descriptions[18]),
        InstagramMinimal(images[19], descriptions[19]),
        InstagramMinimal(images[20], descriptions[20]),
        InstagramMinimal(images[21], descriptions[21]),
        InstagramMinimal(images[22], descriptions[22]),
        InstagramMinimal(images[23], descriptions[23])
    )

}
package com.sort.feriaapp.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.repositories.EventRepository
import com.sort.feriaapp.data.repositories.UserRepository

class ProfileViewModel @ViewModelInject constructor(private val eventRepository: EventRepository, private val userRepository: UserRepository): ViewModel(){

    val images = listOf<String>(
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
        "https://lh3.googleusercontent.com/pw/ACtC-3fMP6vwEj3RDxmxUWzeP3VsCaXC0y4YhMoK7rfUSL7saf08NoO0hZG3YOoNEidS1Sxc5MnlfPrgN0n5LQA_dglx3MK0LbQGZJrGAJQ2UKeDVGJRKCrj_Cy2Mgest6vyl69oj2EOgYOE7eJtaOQKxMhy=w1470-h827-no"
    )

}
package com.sort.feriaapp.viewmodels

import android.os.Build
import android.text.Html
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class OustandingViewModel @ViewModelInject constructor(): ViewModel() {

    val imageURL = "https://lh3.googleusercontent.com/pw/ACtC-3dx3t2FWPaH9CRk7JvvL4KXSW_7_GUcvfgyZg5pA6Rl6svWIuRXcSiO6Kf-Ot7zIUU7gxVPWKny7vyY7g6-zpjt--YH4oO_hSlib0azrV8mB2PpFBidN3DVrKF1FVLuELtjnNzy1dm99C7uBOwdEcoH=w1042-h827-no"

    /*val students = listOf<String>(
        "ANDREA ROSMELI MARTÍNEZ ALARCÓN, estudiante de psicología.",
        "GABRIELA MICHELLE LÓPEZ CORTEZ, estudiante de psicología.",
        "RICARDO ADOLFO MENÉNDEZ CABRERA, estudiante de comunicaciones.",
        "MARCEL MAE-LI RUDAMAS GUEVARA, estudiante de psicología.",
        "CARLOS DANIEL FERNÁNDEZ CASTRO, estudiante de licenciatura en ciencias jurídicas.",
        "EVELING JANNETH RAMÍREZ GUEVARA, estudiante de licenciatura en administración de empresas.",
        "EDGARD ERNESTO LÓPEZ MARTÍNEZ, estudiante de licenciatura en contaduría pública.",
        "LUIS ENRIQUE VELASQUEZ ROMERO, estudiante de ingeniería eléctrica.",
        "MARIELOS ALEJANDRA NAJARRO ALVAREZ, estudiante de ingeniería civil.",
        "PEDRO BENJAMIN ORELLANA COLINDRES estudiante de ingeniería civil.",
        "RICARDO ARMANDO MAGAÑA TOBAR, estudiante de ingeniería eléctrica.",
        "JAVIER ANTONIO ROQUE CORNEJO, estudiante de ingeniería civil.",
        "LUIS FELIPE CHÁVEZ CABALLERO, estudiante de civil.",
        "ERNESTO ALONSO HERNÁNDEZ GUILLÉN, estudiante de informática.",
        "ERICK FERNANDO LEONES AREVALO, estudiante de informática.",
        "CINDY MICHELLE MELÉNDEZ MORALES, estudiante de comunicaciones.",
        "SANDRA NOEMY BENITEZ CRUZ, estudiante de contaduría pública.",
        "JOSÉ RICARDO SÁNCHEZ MONTES, estudiante de mercadeo."
    )*/

    val students = "<ul>\n" +
            "    <li>ANDREA ROSMELI MART&Iacute;NEZ ALARC&Oacute;N, estudiante de psicolog&iacute;a.</li>\n" +
            "    <li>GABRIELA MICHELLE L&Oacute;PEZ CORTEZ, estudiante de psicolog&iacute;a.</li>\n" +
            "    <li>RICARDO ADOLFO MEN&Eacute;NDEZ CABRERA, estudiante de comunicaciones.</li>\n" +
            "    <li>MARCEL MAE-LI RUDAMAS GUEVARA, estudiante de psicolog&iacute;a.</li>\n" +
            "    <li>CARLOS DANIEL FERN&Aacute;NDEZ CASTRO, estudiante de licenciatura en ciencias jur&iacute;dicas.</li>\n" +
            "    <li>EVELING JANNETH RAM&Iacute;REZ GUEVARA, estudiante de licenciatura en administraci&oacute;n de empresas.</li>\n" +
            "    <li>EDGARD ERNESTO L&Oacute;PEZ MART&Iacute;NEZ, estudiante de licenciatura en contadur&iacute;a p&uacute;blica.</li>\n" +
            "    <li>LUIS ENRIQUE VELASQUEZ ROMERO, estudiante de ingenier&iacute;a el&eacute;ctrica.</li>\n" +
            "    <li>MARIELOS ALEJANDRA NAJARRO ALVAREZ, estudiante de ingenier&iacute;a civil.</li>\n" +
            "    <li>PEDRO BENJAMIN ORELLANA COLINDRES estudiante de ingenier&iacute;a civil.</li>\n" +
            "    <li>RICARDO ARMANDO MAGA&Ntilde;A TOBAR, estudiante de ingenier&iacute;a el&eacute;ctrica.</li>\n" +
            "    <li>JAVIER ANTONIO ROQUE CORNEJO, estudiante de ingenier&iacute;a civil.</li>\n" +
            "    <li>LUIS FELIPE CH&Aacute;VEZ CABALLERO, estudiante de civil.</li>\n" +
            "    <li>ERNESTO ALONSO HERN&Aacute;NDEZ GUILL&Eacute;N, estudiante de inform&aacute;tica.</li>\n" +
            "    <li>ERICK FERNANDO LEONES AREVALO, estudiante de inform&aacute;tica.</li>\n" +
            "    <li>CINDY MICHELLE MEL&Eacute;NDEZ MORALES, estudiante de comunicaciones.</li>\n" +
            "    <li>SANDRA NOEMY BENITEZ CRUZ, estudiante de contadur&iacute;a p&uacute;blica.</li>\n" +
            "    <li>JOS&Eacute; RICARDO S&Aacute;NCHEZ MONTES, estudiante de mercadeo.</li>\n" +
            "</ul>"

}
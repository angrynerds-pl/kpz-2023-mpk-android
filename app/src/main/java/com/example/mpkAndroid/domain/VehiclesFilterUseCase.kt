package com.example.mpkAndroid.domain

import javax.inject.Inject

private val BUS_LINES: Set<String> = setOf(
    "A",
    "C",
    "D",
    "K",
    "L",
    "N",
    "3M",
    "100",
    "101",
    "102",
    "103",
    "104",
    "105",
    "106",
    "107",
    "108",
    "109",
    "110",
    "111",
    "112",
    "113",
    "114",
    "115",
    "116",
    "118",
    "119",
    "120",
    "121",
    "122",
    "124",
    "125",
    "126",
    "127",
    "128",
    "129",
    "130",
    "131",
    "132",
    "133",
    "134",
    "136",
    "140",
    "142",
    "143",
    "144",
    "145",
    "146",
    "147",
    "148",
    "149",
    "150",
    "151",
    "206",
    "240",
    "241",
    "242",
    "243",
    "244",
    "245",
    "246",
    "247",
    "248",
    "249",
    "250",
    "251",
    "253",
    "255",
    "257",
    "259",
    "315",
    "319",
    "602",
    "607"
)

private val TRAM_LINES: Set<String> = setOf(
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
    "10",
    "11",
    "15",
    "16",
    "17",
    "20",
    "23",
    "31",
    "32",
    "33",
    "70",
    "74"
)

class VehiclesFilterUseCase @Inject constructor() {

    fun getAllBusLines(): Collection<String> {
        return BUS_LINES
    }

    fun getAllTramLines(): Collection<String> {
        return TRAM_LINES
    }
}
package chrismas.Controller

import chrismas.Data.UserInputData

class DataController {

    fun menuParsing(menuTuple : String){

        val items = menuTuple.split(',')

        for (item in items) {
            val (name, count) = item.split('-')
            UserInputData.menuMap[name] = count.toInt()
        }

    }//메뉴 파싱






}
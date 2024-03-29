package chrismas.Util

import chrismas.Model.UserInputData

object Parsing {

    fun menuParsing(menuTuple : String){

        val items = menuTuple.split(',')

        for (item in items) {
            val (name, count) = item.split('-')
            UserInputData.menuMap[name] = count.toInt()
        }

    }//메뉴 파싱

    fun plusCommaMoney(money: Int) = String.format("%,d", money)



}
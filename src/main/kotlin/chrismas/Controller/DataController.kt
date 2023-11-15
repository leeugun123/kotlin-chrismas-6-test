package chrismas.Controller

import chrismas.Data.MenuPrice
import chrismas.Data.UserInputData

class DataController {

    fun menuParsing(menuTuple : String){

        val items = menuTuple.split(',')

        for (item in items) {
            val (name, count) = item.split('-')
            UserInputData.menuMap[name] = count.toInt()
        }

    }//메뉴 파싱


    fun analysisData(){

        calBeforeTotalMoney()

    }//데이터 분석 및 처리


    private fun calBeforeTotalMoney(){
        MenuPrice.foodInit()//음식 가격 초기화 및 세팅

        for ((name, count) in UserInputData.menuMap) {
            UserInputData.beforeTotalMoney += menuSearching(name) * count
        }

    }//할인 전 총주문 금액 분석

    private fun menuSearching(name : String) : Int {

        return if(MenuPrice.appetizerMap.containsKey(name))
            MenuPrice.appetizerMap[name]!!
        else (if(MenuPrice.mainMap.containsKey(name))
            MenuPrice.mainMap[name]
        else if(MenuPrice.dessertMap.containsKey(name))
            MenuPrice.dessertMap[name]
        else
            MenuPrice.beverageMap[name])!!

    }






}
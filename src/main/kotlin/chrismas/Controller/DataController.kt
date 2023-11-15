package chrismas.Controller

import chrismas.Data.MenuPrice
import chrismas.Data.UserInputData
import chrismas.Util.Parsing

class DataController {


    fun analysisData(){

        calBeforeTotalMoney()//
        calProvideMenu()
        calBenefitContent()
        calTotalDiscount()
        calExpectMoney()
        calBadge()

    }//데이터 분석 및 처리


    private fun calExpectMoney() {

        UserInputData.expectMoney = UserInputData.beforeTotalMoney - UserInputData.benefitMoney

        if(UserInputData.provideMenu != "없음")
            UserInputData.expectMoney += 25000
    }

    private fun calTotalDiscount() {
        UserInputData.benefitMoney =  UserInputData.dDayDiscount + UserInputData.weekendDiscount + UserInputData.weekDiscount + UserInputData.specialDiscount +
        UserInputData.provideEventDiscount
    }
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

    private fun calProvideMenu() {
        if(UserInputData.beforeTotalMoney >= 120000)
            UserInputData.provideMenu = "샴페인 1개"
    }

    private fun calBenefitContent() {

        calDdayDiscount()//디데이 할인
        calWeekDayDiscount()//평일 할인
        calWeekendDayDiscount()//주말 할인
        calSpecialDiscount()//특별 할인
        calProvideDiscount()//증정 할인

        if(discountCheck())
            discountConcat()//할인 합치기
    }


    private fun calDdayDiscount() {

        if(UserInputData.inputDate in 1..25){
            UserInputData.dDayDiscount = 1000 + (UserInputData.inputDate - 1) * 100
        }

    }

    private fun calWeekDayDiscount() {

        val inputDate = UserInputData.inputDate
        val datePatterns = listOf(3, 10, 17, 24, 31)

        if (inputDate in datePatterns.flatMap { it..it+4 }) {
            dessertSearch()
        }

    }

    private fun dessertSearch() {

        UserInputData.weekDiscount += UserInputData.menuMap
            .filterKeys { MenuPrice.dessertMap.containsKey(it) }
            .values
            .sum() * 2023

    }

    private fun calWeekendDayDiscount() {

        val inputDate = UserInputData.inputDate
        val datePatterns = listOf(1, 8 , 15, 22, 29)

        if (inputDate in datePatterns.flatMap { it..it+1 }) {
            mainMenuSearch()
        }

    }

    private fun mainMenuSearch() {

        UserInputData.weekendDiscount += UserInputData.menuMap
            .filterKeys { MenuPrice.mainMap.containsKey(it) }
            .values
            .sum() * 2023

    }

    private fun calSpecialDiscount() {

        val inputDate = UserInputData.inputDate
        val datePatterns = listOf(3, 10 , 17, 24, 25, 31)

        if(inputDate in datePatterns){
            UserInputData.specialDiscount = 1000
        }


    }

    private fun calProvideDiscount() {
        if(UserInputData.provideMenu != "없음")
            UserInputData.provideEventDiscount = 25000
    }


    private fun discountCheck(): Boolean {
        return UserInputData.dDayDiscount != 0 ||
                UserInputData.weekDiscount != 0 ||
                UserInputData.weekendDiscount != 0 ||
                UserInputData.specialDiscount != 0 ||
                UserInputData.provideEventDiscount != 0
    }


    private fun discountConcat() {

        UserInputData.benefitContent = buildString {
            appendDiscount("크리스마스 디데이 할인", UserInputData.dDayDiscount)
            appendDiscount("평일 할인", UserInputData.weekDiscount)
            appendDiscount("주말 할인", UserInputData.weekendDiscount)
            appendDiscount("특별 할인", UserInputData.specialDiscount)
            appendDiscount("증정 이벤트", UserInputData.provideEventDiscount)
        }

    }

    private fun StringBuilder.appendDiscount(title: String, discountAmount: Int) {
        if (discountAmount != 0) {
            append("$title: -${Parsing.plusCommaMoney(discountAmount)}원\n")
        }
    }

    private fun calBadge() {

        UserInputData.badge = when {
            UserInputData.benefitMoney in 5000..< 10000 -> "별"
            UserInputData.benefitMoney in 10000..<20000 -> "트리"
            UserInputData.benefitMoney >= 20000 -> "산타"
            else -> "없음"
        }

    }



}
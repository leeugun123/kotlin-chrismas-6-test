package chrismas.Controller

import chrismas.Data.MenuPrice
import chrismas.Data.UserInputData
import chrismas.Util.Parsing
import chrismas.View.InputView
import chrismas.View.OutputView

class DataController {

    companion object{

        private const val CHAMPAGNE_ONE_GUIDE = "샴페인 1개"
        private const val EVENT_HAPPEN_LEAST_PRICE = 10000
        private const val PROVIDE_CHAMPAGNE_LEAST_PRICE = 120000

        private const val EVENT_START_DATE = 1
        private const val EVENT_END_DATE = 25

        private const val SPECIAL_DISCOUNT_PRICE = 1000
        private const val CHAMPAGNE_PRICE = 25000

        private const val NOTHING = "없음"

        private const val STAR = "별"
        private const val TREE = "트리"
        private const val SANTA = "산타"

        private const val CHRISTMAS_DAY_DISCOUNT = "크리스마스 디데이 할인"
        private const val WEEKDAY_DAY_DISCOUNT = "평일 할인"
        private const val WEEKEND_DAY_DISCOUNT = "주말 할인"
        private const val SPECIAL_DISCOUNT = "특별 할인"
        private const val PROVIDE_EVENT = "증정 이벤트"


        private const val STAR_BADGE_START_PRICE = 5000
        private const val STAR_BADGE_END_PRICE = 10000

        private const val TREE_BADGE_START_PRICE = 10000
        private const val TREE_BADGE_END_PRICE = 20000

        private const val SANTA_BADGE_START_PRICE = 20000

        private const val DISCOUNT_START_PRICE = 1000
        private const val INCREASE_DAY_PRICE = 100

    }

    fun orderProcess(){
        InputView().readInfo()
        analysisData()
        OutputView().outputStart()
    }



    private fun analysisData(){

        calBeforeTotalMoney()
        calProvideMenu()

        if(EVENT_HAPPEN_LEAST_PRICE <= UserInputData.beforeTotalMoney)
            calBenefitContent()

        calTotalDiscount()
        calExpectMoney()
        calBadge()

    }//데이터 분석 및 처리

    private fun calBeforeTotalMoney(){

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
        if(UserInputData.beforeTotalMoney >= PROVIDE_CHAMPAGNE_LEAST_PRICE)
            UserInputData.provideMenu = CHAMPAGNE_ONE_GUIDE
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

        if(UserInputData.inputDate in EVENT_START_DATE..EVENT_END_DATE){
            UserInputData.dDayDiscount = DISCOUNT_START_PRICE + (UserInputData.inputDate - 1) * INCREASE_DAY_PRICE
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
        val weekendDatePatterns = listOf(1, 8 , 15, 22, 29)

        if (inputDate in weekendDatePatterns.flatMap { it..it+1 }) {
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
        val specialDatePatterns = listOf(3, 10 , 17, 24, 25, 31)

        if(inputDate in specialDatePatterns){
            UserInputData.specialDiscount = SPECIAL_DISCOUNT_PRICE
        }


    }

    private fun calProvideDiscount() {
        if(UserInputData.provideMenu != NOTHING)
            UserInputData.provideEventDiscount = CHAMPAGNE_PRICE
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
            appendDiscount(CHRISTMAS_DAY_DISCOUNT, UserInputData.dDayDiscount)
            appendDiscount(WEEKDAY_DAY_DISCOUNT, UserInputData.weekDiscount)
            appendDiscount(WEEKEND_DAY_DISCOUNT, UserInputData.weekendDiscount)
            appendDiscount(SPECIAL_DISCOUNT, UserInputData.specialDiscount)
            appendDiscount(PROVIDE_EVENT, UserInputData.provideEventDiscount)
        }

    }



    private fun StringBuilder.appendDiscount(title: String, discountAmount: Int) {
        if (discountAmount != 0) {
            append("$title: -${Parsing.plusCommaMoney(discountAmount)}원\n")
        }
    }

    private fun calTotalDiscount() {
        UserInputData.benefitMoney =  UserInputData.dDayDiscount + UserInputData.weekendDiscount + UserInputData.weekDiscount + UserInputData.specialDiscount +
                UserInputData.provideEventDiscount
    }

    private fun calExpectMoney() {

        UserInputData.expectMoney = UserInputData.beforeTotalMoney - UserInputData.benefitMoney

        if(UserInputData.provideMenu != NOTHING)
            UserInputData.expectMoney += CHAMPAGNE_PRICE
    }



    private fun calBadge() {

        UserInputData.badge = when {
            UserInputData.benefitMoney in STAR_BADGE_START_PRICE..STAR_BADGE_END_PRICE -> STAR
            UserInputData.benefitMoney in TREE_BADGE_START_PRICE..<TREE_BADGE_END_PRICE -> TREE
            UserInputData.benefitMoney >= SANTA_BADGE_START_PRICE -> SANTA
            else -> NOTHING
        }

    }






}
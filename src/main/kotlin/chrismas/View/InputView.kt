package chrismas.View

import camp.nextstep.edu.missionutils.Console
import chrismas.Controller.DataController
import chrismas.Data.MenuPrice
import chrismas.Data.UserInputData
import chrismas.ExceptionMangement.ExceptionHandle
import chrismas.Util.Parsing


class InputView {

    companion object{
        private const val START_GUIDE =  "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
        private const val VISIT_DATE_QUESTION_GUIDE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        private const val ORDER_MENU_QUESTION_GUIDE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
    }

    fun readInfo(){
        readDate()
        readMenu()
    }

    private fun readDate(){
        startGuide()
        checkReadDateInput()
    }

    private fun readMenu(){
        menuGuide()
        MenuPrice.foodInit()// 메뉴판 데이터 생성
        checkReadMenuInput()
    }

    private fun startGuide(){
        println(START_GUIDE)
        println(VISIT_DATE_QUESTION_GUIDE)

    }

    private fun menuGuide(){ println(ORDER_MENU_QUESTION_GUIDE)}

    private fun checkReadDateInput(){

        while(true){
            try {
                checkDataExceptionHandle()
                return
            }catch (e: IllegalArgumentException) {
                println(e.message)
            }

        }

    }

    private fun checkReadMenuInput(){
        while(true){
            try {
                checkMenuExceptionHandle()
                return
            }catch (e: IllegalArgumentException) { println(e.message) }
        }
    }

    private fun checkDataExceptionHandle(){

        val date = Console.readLine()
        ExceptionHandle.checkDate(date)
        UserInputData.inputDate = date.toInt()

    }

    private fun checkMenuExceptionHandle(){

        val menuTuple = Console.readLine()
        ExceptionHandle.checkMenuInput(menuTuple)
        Parsing.menuParsing(menuTuple)
        ExceptionHandle.checkOnlyBeverage()
        ExceptionHandle.checkExcessOrderCount()

    }



}
package chrismas.View

import camp.nextstep.edu.missionutils.Console
import chrismas.Controller.DataController
import chrismas.Data.UserInputData
import chrismas.ExceptionMangement.ExceptionHandle
import chrismas.Util.Parsing


class InputView {

    fun readInfo(){
        readDate()
        readMenu()
    }

    private fun readDate(){

        startGuide()
        checkReadDateInput()


    }

    private fun readMenu(){
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")

        while(true){
            try {
                val menuTuple = Console.readLine()
                ExceptionHandle.checkMenuInput(menuTuple)
                Parsing.menuParsing(menuTuple)
                return
            }catch (e: IllegalArgumentException) {
                println(e.message)
            }

        }




    }

    private fun startGuide(){
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
    }

    private fun checkReadDateInput(){

        while(true){
            try {
                val date = Console.readLine()
                ExceptionHandle.checkDate(date)
                UserInputData.inputDate = date.toInt()
                return
            }catch (e: IllegalArgumentException) {
                println(e.message)
            }

        }

    }




}
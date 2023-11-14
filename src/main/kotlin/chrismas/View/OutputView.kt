package chrismas.View

import chrismas.Data.UserInputData

class OutputView {

    fun outputStart(){
        println("12월 ${UserInputData.inputDate}일에 우테코 식당에서 받을 혜택 미리 보기!" + "\n")
        printBenfitInfo()
    }

    private fun printBenfitInfo(){
        orderMenu()
        beforeDiscountTotalMoney()
        provideMenu()
        benefitContent()
        benfitMoney()
        expectPayMoney()
        eventBadge()
    }

    private fun orderMenu(){
        println("<주문 메뉴>")
        for ((name, count) in UserInputData.menuMap) {
            println("$name $count" + "개")
        }
        println()
    }

    private fun beforeDiscountTotalMoney(){
        println("<할인 전 총주문 금액>")
        println("${UserInputData.beforeTotalMoney}원" + "\n")
    }

    private fun provideMenu(){
        println("<증정 메뉴>")
        println(UserInputData.provideMenu + "\n")
    }

    private fun benefitContent(){
        println("<혜택 내역>")
        println(UserInputData.benefitContent + "\n")
    }

    private fun benfitMoney(){
        println("<총혜택 금액>")
        println("${UserInputData.benefitMoney}원" + "\n")
    }

    private fun expectPayMoney(){
        println("<할인 후 예상 결제 금액>")
        println("${UserInputData.expectMoney}원" + "\n")
    }

    private fun eventBadge(){
        println("<12월 이벤트 배지>")
        println(UserInputData.badge)
    }



}
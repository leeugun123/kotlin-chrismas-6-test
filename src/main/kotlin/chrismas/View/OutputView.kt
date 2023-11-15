package chrismas.View

import chrismas.Data.UserInputData
import chrismas.Util.Parsing

class OutputView {

    fun outputStart(){
        println("12월 ${UserInputData.inputDate}일에 우테코 식당에서 받을 혜택 미리 보기!" + "\n")
        printBenfitInfo()
    }

    private fun printBenfitInfo(){
        orderMenuPrint()
        beforeDiscountTotalMoneyPrint()
        provideMenuPrint()
        benefitContentPrint()
        benfitMoneyPrint()
        expectPayMoneyPrint()
        eventBadgePrint()
    }

    private fun orderMenuPrint(){
        println("<주문 메뉴>")
        for ((name, count) in UserInputData.menuMap) {
            println("$name $count" + "개")
        }
        println()
    }

    private fun beforeDiscountTotalMoneyPrint(){
        println("<할인 전 총주문 금액>")
        println(Parsing.plusCommaMoney(UserInputData.beforeTotalMoney) + "원" + "\n")
    }

    private fun provideMenuPrint(){
        println("<증정 메뉴>")
        println(UserInputData.provideMenu + "\n")
    }

    private fun benefitContentPrint(){
        println("<혜택 내역>")
        println(UserInputData.benefitContent)
    }

    private fun benfitMoneyPrint() {
        println("<총혜택 금액>")
        val benefitMoneyString = Parsing.plusCommaMoney(UserInputData.benefitMoney)

        val result = if (UserInputData.benefitMoney >= 10000) "-$benefitMoneyString"+ "원\n" else benefitMoneyString + "원\n"
        println(result)
    }


    private fun expectPayMoneyPrint(){
        println("<할인 후 예상 결제 금액>")
        println(Parsing.plusCommaMoney(UserInputData.expectMoney)+ "원" + "\n")
    }

    private fun eventBadgePrint(){
        println("<12월 이벤트 배지>")
        print(UserInputData.badge)
    }




}
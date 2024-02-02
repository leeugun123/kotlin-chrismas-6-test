package chrismas.View

import chrismas.Model.UserInputData
import chrismas.Util.Parsing

class OutputView {

    companion object{
        private const val EVENT_MONTH = "12월"
        private const val BENEFIT_PREVIEW_GUIDE = "일에 우테코 식당에서 받을 혜택 미리 보기!" + "\n"
        private const val ORDER_MENU_KOREAN = "<주문 메뉴>"
        private const val BEFORE_DISCOUNT_TOTAL_ORDER_PRICE = "<할인 전 총주문 금액>"
        private const val PRESENTATION_MENU = "<증정 메뉴>"
        private const val BENEFIT_CONTENT = "<혜택 내역>"
        private const val TOTAL_BENEFIT_PRICE = "<총혜택 금액>"
        private const val AFTER_EXPECT_PAY_MONEY = "<할인 후 예상 결제 금액>"
        private const val DECEMBER_EVENT_BADGE = "<12월 이벤트 배지>"
    }

    fun outputStart(){
        println(EVENT_MONTH + " ${UserInputData.inputDate}" + BENEFIT_PREVIEW_GUIDE)
        printBenefitInfo()
    }

    private fun printBenefitInfo(){
        orderMenuPrint()
        beforeDiscountTotalMoneyPrint()
        provideMenuPrint()
        benefitContentPrint()
        benefitMoneyPrint()
        expectPayMoneyPrint()
        eventBadgePrint()
    }

    private fun orderMenuPrint(){
        println(ORDER_MENU_KOREAN)
        for ((name, count) in UserInputData.menuMap) {
            println("$name $count" + "개")
        }
        println()
    }

    private fun beforeDiscountTotalMoneyPrint(){
        println(BEFORE_DISCOUNT_TOTAL_ORDER_PRICE)
        println(Parsing.plusCommaMoney(UserInputData.beforeTotalMoney) + "원" + "\n")
    }

    private fun provideMenuPrint(){
        println(PRESENTATION_MENU)
        println(UserInputData.provideMenu + "\n")
    }

    private fun benefitContentPrint(){
        println(BENEFIT_CONTENT)
        println(UserInputData.benefitContent)
    }

    private fun benefitMoneyPrint() {
        println(TOTAL_BENEFIT_PRICE)
        val benefitMoneyString = Parsing.plusCommaMoney(UserInputData.benefitMoney)

        val result = if (UserInputData.benefitMoney >= 10000) "-$benefitMoneyString"+ "원\n" else benefitMoneyString + "원\n"
        println(result)
    }


    private fun expectPayMoneyPrint(){
        println(AFTER_EXPECT_PAY_MONEY)
        println(Parsing.plusCommaMoney(UserInputData.expectMoney)+ "원" + "\n")
    }

    private fun eventBadgePrint(){
        println(DECEMBER_EVENT_BADGE)
        print(UserInputData.badge)
    }




}
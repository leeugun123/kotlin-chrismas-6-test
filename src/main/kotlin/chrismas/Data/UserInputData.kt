package chrismas.Data

object UserInputData {

    var inputDate  = 0 //방문 날짜
    var menuMap = hashMapOf<String,Int>() // 고객이 시킨 매뉴

    var beforeTotalMoney = 0 //할인 전 총 주문 금액
    var provideMenu = "없음" // 증정 메뉴

    var benefitContent = "없음\n" //혜택 내역

    var dDayDiscount = 0 //크리스마스 디데이 할인
    var weekDiscount = 0 //평일 할인
    var weekendDiscount = 0 //주말 할인
    var specialDiscount = 0 //특별 할인
    var provideEventDiscount = 0 // 증정 이벤트

    var benefitMoney = 0 //총혜택 금액
    var expectMoney = 0 // 할인 후 예상 결제 금액
    var badge = "" // 12월 이벤트 배지

}
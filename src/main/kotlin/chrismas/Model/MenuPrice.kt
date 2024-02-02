package chrismas.Model

object MenuPrice {

    val appetizerMap = hashMapOf<String,Int>()
    val mainMap = hashMapOf<String,Int>()
    val dessertMap = hashMapOf<String,Int>()
    val beverageMap = hashMapOf<String,Int>()

    fun foodInit(){
        appetizerMapInit()
        mainMapInit()
        dessertMapInit()
        beverageMapInit()
    }

    private fun appetizerMapInit(){
        appetizerMap["양송이수프"] = 6000
        appetizerMap["타파스"] = 5500
        appetizerMap["시저샐러드"] = 8000
    }

    private fun mainMapInit(){
        mainMap["티본스테이크"] = 55000
        mainMap["바비큐립"] = 54000
        mainMap["해산물파스타"] = 35000
        mainMap["크리스마스파스타"] = 25000
    }

    private fun dessertMapInit(){
        dessertMap["초코케이크"] = 15000
        dessertMap["아이스크림"] = 5000
    }

    private fun beverageMapInit(){
        beverageMap["제로콜라"] = 3000
        beverageMap["레드와인"] = 60000
        beverageMap["샴페인"] = 25000
    }


}
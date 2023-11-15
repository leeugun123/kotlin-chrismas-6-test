package chrismas.ExceptionMangement

import chrismas.Data.MenuPrice

object ExceptionHandle {

    private const val ERROR = "[ERROR]"
    private const val INPUT_AGAIN = "다시 입력해 주세요."
    private const val WRONG_DATE = "$ERROR 유효하지 않는 날짜입니다.$INPUT_AGAIN"
    private const val INVALID_FORMAT = "$ERROR 잘못된 형식입니다.$INPUT_AGAIN"
    private const val INVALID_MENU_ORDER = "$ERROR 유효하지 않은 주문입니다.$INPUT_AGAIN"

    fun checkDate(date : String){
        require(checkValidDate(date)){ INVALID_FORMAT}
        require(checkDateInRange(date)){ WRONG_DATE}
    }

    fun checkMenuInput(menuOrder : String){

        require(checkValidMenuFormat(menuOrder) && checkMenuAll(menuOrder)) { INVALID_MENU_ORDER}

    }

    private fun checkMenuAll(menuOrder: String): Boolean {
        val uniqueMenus = HashSet<String>()

        for (entry in menuOrder.split(",")) {
            val (menu, countStr) = entry.split("-")
            val count = countStr.toInt()

            if (count <= 0 || !uniqueMenus.add(menu) || !checkExistMenu(menu)) {
                return false
            }

        }
        return true
    }


    private fun checkExistMenu(menu: String) = menu in MenuPrice.appetizerMap + MenuPrice.mainMap + MenuPrice.dessertMap + MenuPrice.beverageMap

    private fun checkValidDate(date : String) = date.toIntOrNull() != null

    private fun checkDateInRange(date : String) = date.toInt() in 1..31

    private fun checkValidMenuFormat(menu : String) : Boolean = Regex("""^[a-zA-Z가-힣]+\-\d+(,[a-zA-Z가-힣]+\-\d+)*$""").matches(menu)




}
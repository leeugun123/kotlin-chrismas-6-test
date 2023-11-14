package chrismas.View

import chrismas.Controller.DataController

class OrderUI {

    fun orderProcess(){
        InputView().readInfo()
        DataController().analysisData()
        OutputView().outputStart()
    }


}
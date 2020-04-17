package dev.jonaz.backend.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@Controller
class CustomErrorController : ErrorController {

    @RequestMapping("/error")
    fun handleError(
            request: HttpServletRequest,
            model: Model
    ): ModelAndView = when (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)) {
        HttpStatus.NOT_FOUND.value() -> ModelAndView("shared/error/404")
        else                         -> {
            model.addAttribute("code", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString().toInt())
            ModelAndView("shared/error/error")
        }
    }

    override fun getErrorPath(): String = "/error"
}

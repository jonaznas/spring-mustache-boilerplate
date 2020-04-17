package dev.jonaz.backend.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class IndexController {
    @Value("\${name}")
    private val name: String? = null

    @GetMapping("/")
    fun main(model: Model): ModelAndView {
        model.addAttribute("siteName", name)
        model.addAttribute("test", "This is a test variable")
        return ModelAndView("index")
    }
}

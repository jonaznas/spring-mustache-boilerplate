package dev.jonaz.backend.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.lang.reflect.Member
import java.lang.reflect.Modifier

@Controller
class IndexController {
    @Value("\${name}")
    private val name: String? = null

    @GetMapping("/")
    fun main(model: Model): ModelAndView {
        model.addAttribute("siteName", name)
        model.addAttribute("text", "This is a test variable")
        return ModelAndView("index")
    }

    @PostMapping("/")
    @ResponseBody
    fun submitText(
            model: Model,
            @RequestParam("myText") text: String
    ): ModelAndView {
        model.addAttribute("siteName", name)
        model.addAttribute("text", "Your text is $text")

        return ModelAndView("index")
    }
}

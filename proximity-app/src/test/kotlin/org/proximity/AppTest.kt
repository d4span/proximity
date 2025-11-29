package org.proximity

import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun appHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}

@SpringBootTest
class ApplicationContextTest {
    @Test
    fun contextLoads() {
    }
}

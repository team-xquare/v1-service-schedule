package app.xqaure.schedule.global.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Get slf4j logger by class to call
 *
 * &nbsp
 *
 * **Typical usage pattern:**
 * ```
 * class A {
 *      val logger = logger()
 *
 *      fun a() {
 *          logger.error("Something went wrong.")
 *      }
 * }
 * ```
 *
 * @see org.slf4j.LoggerFactory.getLogger
 * @return [org.slf4j.Logger]
 */
inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)

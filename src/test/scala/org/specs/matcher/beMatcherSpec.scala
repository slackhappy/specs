package org.specs.matcher
import org.specs.specification._
  
class beMatcherSpec extends MatchersSpecification {  outer =>
  "A matcher starting with 'be' can be used with 'be' as a separated word" in {
    "hello" must be equalTo("hello") 
    expectation("hello" must be equalTo("hello2")) must failWithMatch(".*")
  }
  "A matcher starting with 'notBe' can be used with 'not be' as a separated word" in {
    "hello" must not be equalTo("world") 
    expectation("hello" must not be equalTo("hello")) must failWithMatch(".*")
  }
  "not be ==" in {
    "hello" must not be ==("world") 
    expectation("hello" must not be ==("hello")) must failWithMatch(".*")
  }
  "be asNullAs" in {
    var s: String = null
    var s2: String = null
    s must be asNullAs(s2)
    expectation(s must be asNullAs("")) must failWithMatch(".*")
  }
  "not be asNullAs" in {
    var s: String = null
    var s2: String = ""
    s must not be asNullAs(s2)
    expectation(s must not be asNullAs(null)) must failWithMatch(".*")
  }
  "be in" in {
    "hello" must be in(List("hello")) 
    expectation("hello" must be in(List("hello2"))) must failWithMatch(".*")
  }
  "not be in" in {
    "hello" must not be in(List("world")) 
    expectation("hello" must not be in(List("hello"))) must failWithMatch(".*")
  }
  "be oneOf" in {
    "hello" must be oneOf("hello", "world") 
    expectation("hello" must be oneOf("hi", "world")) must failWithMatch(".*")
  }
  "not be oneOf" in {
    "hello" must not be oneOf("world") 
    expectation("hello" must not be oneOf("hello", "world")) must failWithMatch(".*")
  }

  implicit def toStringResultMatcher(result: Result[String]) = new StringResultMatcher(result)
  class StringResultMatcher(result: Result[String]) {
    def matching(s: String) = result.matchWith(beMatching(s))
  }
  def matching(s: String) = beMatching(s)

  "A matcher starting with 'be' can be used with 'be' as a separated word" in {
    "hello" must be matching("h.*") 
  }
  "A matcher starting with 'notBe' can be used with 'not be' as separated words" in {
    "hello" must not be(matching("z.*"))
  }
}
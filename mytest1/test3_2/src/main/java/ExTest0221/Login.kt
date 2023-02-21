package ExTest0221

import java.util.*

// Scanner scanner = new Scanner(System.in) 자바 버전.
val scanner: Scanner = Scanner(System.`in`);

class Login() {
       companion object {
                fun loginTest(user:User){
                    if(user.id.equals("admin") && user.pw.equals("1234")) {
                        println("로그인 성공")
                    }
                    
                }
    }
}

fun main() {

    println("ID 입력하세요: ")
   val id = scanner.nextLine()

    println("PW 입력하세요: ")
    val pw = scanner.nextLine()

    println("EMAIL 입력하세요: ")
    val email = scanner.nextLine()

    println("PHONE 입력하세요: ")
    val phone = scanner.nextLine()
    val lsy = User(id,pw,email,phone)

    Login.loginTest(lsy)

}
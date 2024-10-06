import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val num = br.readLine().toInt()
    val grapes = IntArray(size = num)
    val scores = IntArray(size = num)
    for (i in 0 until num){
        grapes[i] = br.readLine().toInt()
    }

    scores[0] = grapes[0]
    if(num > 1){
        scores[1] = grapes[1] + scores[0]
    }
    if(num > 2){
        scores[2] = max(max(scores[0], grapes[1]) + grapes[2], scores[1])
    }

    for(i in 3 until num){
        scores[i] = max(max(scores[i-2], scores[i-3] + grapes[i-1]) + grapes[i], scores[i-1])
    }

    bw.write(scores.maxOrNull().toString())
    bw.flush()
    bw.close()
}

/*
더 효율적인 코드
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val num = br.readLine().toInt()

    // 포도주 양 입력 받기
    val grapes = IntArray(num) { br.readLine().toInt() }

    // 1잔 이하일 경우 바로 출력
    if (num == 1) {
        bw.write(grapes[0].toString())
        bw.flush()
        bw.close()
        return
    }

    // 포도주 시음 점수를 기록할 배열
    val scores = IntArray(num)
    scores[0] = grapes[0]
    scores[1] = grapes[0] + grapes[1]

    if (num > 2) {
        scores[2] = max(grapes[2] + max(grapes[0], grapes[1]), scores[1])
    }

    // 점수 계산
    for (i in 3 until num) {
        scores[i] = max(scores[i-1], max(scores[i-2] + grapes[i], scores[i-3] + grapes[i-1] + grapes[i]))
    }

    // 최대 점수 출력
    bw.write(scores[num - 1].toString())
    bw.flush()
    bw.close()
}


 */

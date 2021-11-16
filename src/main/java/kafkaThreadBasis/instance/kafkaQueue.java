package kafkaThreadBasis.instance;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by sblim
 * Date : 2021-11-16
 * Time : 오후 3:04
 */

// LinkedBlockingDeque -> Linkded node로 이루어진 Deque. Integer.MAX_VALUE의 크기까지만 생성이 가능
public class kafkaQueue extends LinkedBlockingDeque<String> {

    private static volatile kafkaQueue _instance = null;

    /** 기본 생성자 */
    public kafkaQueue() {

    }

    public static kafkaQueue getInstance() {
        if( _instance == null ) {
            /* 제일 처음에만 동기화 하도록 함 */
            synchronized(kafkaQueue.class) {
                if( _instance == null ) {
                    _instance = new kafkaQueue();
                }
            }
        }
        return _instance;
    }

}
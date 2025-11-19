package woowa.myapp;

import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;

public class PresetDeckGenerator {

    // 수능 필수 영단어 (원하면 더 많이 넣어줄 수 있습니다)
    private static final String[][] ESSENTIAL_WORDS = {
            {"abandon", "버리다, 포기하다"},
            {"ability", "능력"},
            {"absorb", "흡수하다"},
            {"academic", "학문의"},
            {"access", "접근하다"},
            {"accomplish", "이루다"},
            {"accurate", "정확한"},
            {"achieve", "달성하다"},
            {"adapt", "적응하다"},
            {"adjust", "조절하다"},
            {"affect", "영향을 미치다"},
            {"afford", "여유가 있다"},
            {"aggressive", "공격적인"},
            {"alter", "바꾸다"},
            {"analyze", "분석하다"},
            {"approach", "접근하다"},
            {"appropriate", "적절한"},
            {"approve", "승인하다"},
            {"arrange", "정리하다, 배열하다"},
            {"assist", "돕다"},
            {"assume", "가정하다"},
            {"atmosphere", "대기, 분위기"},
            {"attempt", "시도하다"},
            {"average", "평균"},
            {"benefit", "이익, 혜택"},
            {"calculate", "계산하다"},
            {"capacity", "능력, 용량"},
            {"characteristic", "특징"},
            {"collapse", "붕괴되다"},
            {"combine", "결합하다"},
            {"comment", "논평하다"},
            {"commerce", "상업"},
            {"commit", "저지르다, 약속하다"},
            {"compare", "비교하다"},
            {"complain", "불평하다"},
            {"complete", "완료하다"},
            {"confirm", "확인하다"},
            {"consider", "고려하다"},
            {"construct", "건설하다"},
    };

    private static final String[][] JAPANESE_WORDS = {
            {"行く（いく）", "가다"},
            {"来る（くる）", "오다"},
            {"見る（みる）", "보다"},
            {"食べる（たべる）", "먹다"},
            {"飲む（のむ）", "마시다"},
            {"速い（はやい）", "빠르다"},
            {"遅い（おそい）", "느리다"},
            {"きれい", "예쁘다, 깨끗하다"},
            {"大きい（おおきい）", "크다"},
            {"小さい（ちいさい）", "작다"},
            {"高い（たかい）", "높다, 비싸다"},
            {"安い（やすい）", "싸다"},
            {"暑い（あつい）", "덥다"},
            {"寒い（さむい）", "춥다"},
            {"新しい（あたらしい）", "새롭다"},
            {"古い（ふるい）", "오래되다"},
    };

    private static final String[][] CHINESE_WORDS = {
            {"去（qù）", "가다"},
            {"来（lái）", "오다"},
            {"看（kàn）", "보다"},
            {"吃（chī）", "먹다"},
            {"喝（hē）", "마시다"},
            {"快（kuài）", "빠르다"},
            {"慢（màn）", "느리다"},
            {"漂亮（piàoliang）", "예쁘다"},
            {"大（dà）", "크다"},
            {"小（xiǎo）", "작다"},
            {"高（gāo）", "높다"},
            {"低（dī）", "낮다"},
            {"热（rè）", "덥다"},
            {"冷（lěng）", "춥다"},
            {"新（xīn）", "새롭다"},
            {"旧（jiù）", "오래되다"},
    };

    private static final String[][] IT_WORDS = {
            {"교착상태 4조건", "상호배제 / 점유와 대기 / 비선점 / 환형대기"},
            {"TCP 특징", "연결형 / 신뢰성 / 순서보장"},
            {"UDP 특징", "비연결형 / 비신뢰성 / 빠름"},
            {"정규화 1NF", "원자값"},
            {"정규화 2NF", "부분 함수 종속 제거"},
            {"정규화 3NF", "이행적 종속 제거"},
            {"트랜잭션 ACID", "원자성 / 일관성 / 고립성 / 지속성"},
            {"폭포수 모델", "순차적 개발 모델"},
            {"애자일", "반복적 / 점증적 개발"},
            {"캡슐화", "데이터 은닉, 정보 보호"},
            {"상속", "기존 클래스를 확장"},
            {"다형성", "동일한 호출, 다른 동작"},
            {"응집도", "모듈 내부 기능 연관성"},
            {"결합도", "모듈 간 의존성"},
            {"페이지 교체 알고리즘", "FIFO / LRU / LFU"},
            {"OSI 7계층", "물리 - 데이터링크 - 네트워크 - 전송 - 세션 - 표현 - 응용"},
    };

    /**
     * 수능 필수 영단어 덱을 자동 생성하여 DeckManager에 추가하는 메서드
     */
    public static void addEssentialVocabularyDeck(DeckManager deckManager) {

        Deck deck = new Deck("수능 필수 영단어");

        for (String[] w : ESSENTIAL_WORDS) {
            String front = w[0];  // 영어단어
            String back = w[1];   // 뜻
            deck.addCard(new Card(front, back));
        }

        deckManager.addDeck(deck);
        deckManager.saveDeckList();
        deck.saveDeckData();

        System.out.println("수능 필수 영단어 덱이 자동 생성되었습니다. 단어 개수: "
                + ESSENTIAL_WORDS.length);
    }

    public static void addJapaneseDeck(DeckManager deckManager) {

        Deck deck = new Deck("일본어 기본 단어");

        for (String[] w : JAPANESE_WORDS) {
            deck.addCard(new Card(w[0], w[1]));
        }

        deckManager.addDeck(deck);
        deckManager.saveDeckList();
        deck.saveDeckData();

        System.out.println("일본어 기본 단어 덱 생성 완료 (개수: " + JAPANESE_WORDS.length + ")");
    }

    public static void addChineseDeck(DeckManager deckManager) {

        Deck deck = new Deck("중국어 기본 단어");

        for (String[] w : CHINESE_WORDS) {
            deck.addCard(new Card(w[0], w[1]));
        }

        deckManager.addDeck(deck);
        deckManager.saveDeckList();
        deck.saveDeckData();

        System.out.println("중국어 기본 단어 덱 생성 완료 (개수: " + CHINESE_WORDS.length + ")");
    }

    public static void addITDeck(DeckManager deckManager) {

        Deck deck = new Deck("정처기 핵심 개념");

        for (String[] w : IT_WORDS) {
            deck.addCard(new Card(w[0], w[1]));
        }

        deckManager.addDeck(deck);
        deckManager.saveDeckList();
        deck.saveDeckData();

        System.out.println("정처기 핵심 개념 덱 생성 완료 (개수: " + IT_WORDS.length + ")");
    }

}

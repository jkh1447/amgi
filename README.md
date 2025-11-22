# 암기빵

앞면과 뒷면을 가진 플래시카드형식의 암기 프로그램입니다.

## 화면 구성

### 메인화면(덱 목록)
<img width="786" height="593" alt="image" src="https://github.com/user-attachments/assets/3b6ef23e-6080-41f7-8fed-de49017c05e7" />

### 학습화면
<img width="786" height="593" alt="image" src="https://github.com/user-attachments/assets/924f8c26-6346-4d87-9d8d-ca8465353c9b" />

### 카드 추가화면
<img width="786" height="593" alt="image" src="https://github.com/user-attachments/assets/3ff2631e-a106-4e8a-8b8b-c76e3449c553" />

### 설정화면
<img width="786" height="593" alt="image" src="https://github.com/user-attachments/assets/79481f22-2f31-4be7-9c1b-475cd02eddee" />

어떤 카드를 학습할 지 설정할 수 있습니다.

### 통계화면
<img width="786" height="593" alt="image" src="https://github.com/user-attachments/assets/c41626c9-e471-498e-b20c-1a855f8062b1" />

어떤 카드를 어려워하고 있는지 막대 그래프로 확인할 수 있습니다.

### 실행파일
[암기빵](https://github.com/jkh1447/amgi/releases/download/v1.0/amgibbang.jar)

<br>

## 구현할 기능 목록

### 메인화면
- [x] 프로그램의 이름, 사이즈, 위치등을 설정
- [x] 메인 프레임에서 패널을 교체하는 기능

### 덱 목록
- [x] 등록된 덱의 목록을 불러옴
- [x] 각 덱마다 이름, 추가버튼, 설정버튼, 삭제버튼 가진 패널 생성
- [x] 덱 패널을 수직으로 나열, 스크롤 가능
- [x] 새로운 덱을 생성하는 버튼

### 덱 추가
- [x] 새로운 덱의 이름을 입력받을 다이얼로그(JDialog)

### 덱 설정
- [x] 덱에 등록된 카드들을 나열하는 패널 생성
- [x] 카드를 체크하여 학습할 카드들을 선택 (기본은 모두 선택)
- [x] 삭제할 카드를 체크하여 일괄삭제

### 덱 학습
- [x] 카드의 앞면을 출력하고 정답보기 버튼을 통해 뒷면을 출력
- [x] 정답확인 후 쉬움, 다시 버튼 표시
- [x] 쉬움 버튼 클릭하면 타겟에서 제외, 다시버튼 누르면 아무동작 x

### 덱 통계
- [x] 카드마다 난이도에 따라 내림차순으로 정렬된 상위 10개 카드에 대해 막대 그래프로 표시
- [x] 막대에 마우스 포인터를 올려놓으면 그 카드에 대한 정보를 표시

### 카드 추가
- [x] 앞면과 뒷면 textfield
- [x] 저장 버튼으로 카드를 덱에 저장

### 덱 매니저
- [x] 덱을 추가
- [x] 덱목록을 반환
- [x] 덱을 삭제
- [x] 덱 목록을 파일로 저장
- [x] 덱 목록을 불러오기

### 덱
- [x] 덱의 이름 반환
- [x] 덱에 카드를 추가
- [x] 카드 리스트를 반환
    - lazyload를 위해서 카드에 접근할때 파일에서 불러옴
- [x] 덱을 파일로 저장
- [x] 덱을 파일에서 불러오기
- [x] 덱 파일을 삭제

### 카드
- [x] 카드 앞면을 반환
- [x] 카드 뒷면을 반환
- [x] 학습할 카드인지 나타내는 boolean변수
- [x] 학습할 카드로 설정
- [x] 각 난이도별 클릭횟수를 설정
- [x] 클릭횟수를 기반으로 난이도 반환

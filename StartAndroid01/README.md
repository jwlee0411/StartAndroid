# StartAndroid01
아래는 Android Studio에서 설치가 잘 안 될 때 참고하면 좋을 자료입니다. <br>
정상적으로 설치가 된다면 읽지 않으셔도 좋습니다. <br>
아래 내용은 모두 윈도우10 기준입니다. <br>

## 설치 전
##### 운영체제의 사용자명이 한글로 되어 있는지 확인해 주세요.
##### <pre>C:\Users\(유저명)</pre>
##### 이 한글로 되어 있으면 Android Studio가 정상적으로 설치되지 않습니다.


## 설치 후
##### 1.
##### <pre>The emulator process ~ was killed</pre>
##### 내 PC > 우클릭 > 속성 > 고급시스템설정 > 고급 > 환경변수 설정
##### 시스템 변수 > PATH > 변수 편집
##### 기존에 있던 거 뒤에 아래 내용 추가
##### <pre>sdk location\tool; sdk location\platform-tools;</pre>

##### 2. 
##### <pre>Intel HAXM ~ 관련 에러 </pre>
##### a. 제어판> 프로그램> 프로그램 및 기능> 왼쪽에 Windows 기능 켜기 / 끄기 
##### Windows 하이퍼 바이저 플랫폼 활성화
##### b. 바이오스 가서 SMT 관련 옵션 활성화

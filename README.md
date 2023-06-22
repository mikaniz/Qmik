# Qmik
> Type Safe를 지원하는 SQL Mapper

### Qmik의 목적
1. Type Safe
2. DBMS 종속성 제거
3. 쿼리를 편하게 작성

### Type Safe 지원을 위해
어떤 방식으로 `Type Safe`를 지원하도록 구현할지 고민한 결과, 데이터베이스 정보를 읽어와서 클래스로 저장해두고 해당 클래스에 담긴 각각의 타입 정보를 참조해서 사용하기로 결정하였습니다.
[아래의 시퀀스 다이어그램](#sequence-diagram)을 통해 해당 과정을 확인할 수 있습니다.
이를 통해 생성된 Model 클래스에서는 Attribute에 Generic Type을 설정하도록 하여 저장한 정보에 적합한 타입만을 받을 수 있도록 합니다.

또한 `Model`이라는 이름의 마커 인터페이스를 만든 뒤 모든 Model 클래스가 해당 인터페이스를 구현하도록 하였습니다.
그리고 쿼리에서 Model 클래스를 지정하는 부분에 `Model`을 구현한 클래스만 입력될 수 있도록 처리하였습니다.

### Sequence Diagram
- 데이터베이스 정보를 반영한 Model 클래스를 생성하는 과정
  ![image](https://github.com/mikaniz/Qmik/assets/92143119/7ea0f2ad-3a5c-46e4-888c-74ef1b2e04a9)
  - 사용자가 qmik-config.xml 파일에 데이터베이스 정보를 설정해두고 ModelGenerator를 호출합니다.
  - ModelGenerator는 ConfigHandler라는 XML Parser를 통해 해당 파일을 파싱하고 데이터베이스 정보를 담은 리스트를 얻어옵니다.
  - 이후 MetaDataLoader를 호출하여 각 데이터베이스의 모든 테이블 정보를 담은 리스트를 얻게 됩니다.
  - 마지막으로 각 테이블 정보를 통해 각각의 Model 클래스를 생성합니다.
- 쿼리를 생성하고 실행하는 과정
  ![image](https://github.com/mikaniz/Qmik/assets/92143119/beb02eca-7e55-4a5d-bdc5-293a99b69ac5)

### Class Diagram
![qmik drawio](https://github.com/mikaniz/Qmik/assets/92143119/e07f02ac-9cbd-4f13-9a1f-81b95fb6b43b)
- com.qmik.model
  - Model 클래스 생성을 위한 패키지
- com.qmik.database
  - 데이터베이스 접근 및 데이터베이스 메타데이터 저장을 위한 패키지
- com.qmik.query
  - 쿼리 생성 및 실행을 위한 패키지

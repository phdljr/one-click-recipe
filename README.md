# 🍽 딸깍! 레시피

## 📚 목차
- [프로젝트 소개](#프로젝트-소개)
- [팀원 소개](#팀원-소개)
- [Ground Rules](#ground-rules)
- [목표](#목표)
- [우리들의 약속](#우리들의-약속)
- [기술 스택](#기술-스택)
- [기술적 의사 결정](#기술적-의사-결정)
- [트러블 슈팅](#트러블-슈팅)


## 🌟 프로젝트 소개
**한 줄 정리**: 내가 원하는 레시피의 재료를 클릭 한번으로 주문할 수 있는 서비스.

레시피를 따라 요리를 하고 싶을 때, 필요한 재료를 일일이 구매하는 번거로움을 해결하기 위해 `딸깍! 레시피`가 탄생했습니다. 이 서비스를 통해 사용자는 원하는 레시피에 나열된 모든 재료를 버튼 클릭 한 번으로 쉽게 주문할 수 있습니다.


![움짤](https://github.com/phdljr/one-click-recipe/assets/141345981/738ba62f-2ba8-4cc7-8070-e5dbe24c08ae)

## 👥 팀원 소개

|      | 이종렬                                                              | 배규태                                                                | 박창선                                   | 고도윤                                        |
|------|------------------------------------------------------------------|--------------------------------------------------------------------|---------------------------------------|--------------------------------------------|
| 사진   | <img src="https://avatars.githubusercontent.com/u/68233342?v=4"> | <img src="https://avatars.githubusercontent.com/u/141345981?v=4">  | <img src="https://avatars.githubusercontent.com/u/107198171?v=4">                                     | <img src="https://avatars.githubusercontent.com/u/127570031?v=4">                                          |
| 이름   | 이종렬                                                              | 배규태                                                                | 박창선                                   | 고도윤                                        |
| 성격 유형 | ESFJ                                                             | INTP                                                               | ENFP                                  | INFP                                       |
| 블로그  | [블로그](https://velog.io/@phdljr)                                  | [블로그](https://velog.io/@qoxowkd0716/posts)                         | [블로그](https://mdpang.tistory.com/)    | [블로그](https://velog.io/@rhehdbs0621/posts) |
| GitHub | <a href="https://github.com/phdljr">![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)</a>                              | <a href="https://github.com/baegyutae">![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)</a>                             | <a href="https://github.com/pangseon">![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)</a> | <a href="https://github.com/kodoyoon">![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)</a>     |
| 담당 기능 |    - 로그인<br>- 회원가입<br>- 카카오 페이 연동<br>- 레시피 생성<br>- CI/CD (Netlify 구축, Github Actions 구축)<br>- Infra (AWS EC2, RDS, ELB, ACM 설정)                                                        | - 주문, 주문 상세 내역<br>- 카트<br>- 프론트엔드 (주문 내역 및 상세 내역, 관리자 페이지, 마이 페이지) | - S3 연결 및 CUD 구현<br>- 소셜 로그인 연결 <br>- 레시피 CRUD<br>- 레시피 조리과정 CRUD<br>- 레시피 식재료 CRUD<br>- 식재료 CRUD                           | - 구독<br>- 레시피 좋아요<br>- 리뷰(댓글)<br>- 비밀번호, 닉네임 수정<br>- 회원탈퇴                                  |


## 📜 Ground Rules
1. 연락은 반드시 응답하되, 사전에 이유를 밝히거나 즉시 설명하기.
2. 모르는 것은 솔직히 인정하고, 확실한 정보만 전달하기.
3. 작업 상황을 실시간으로 공유하고, 화면 공유를 통해 협업하기.
4. 할 수 없는 일은 솔직히 말하고, 도움이나 역할 변경 요청하기.
5. 의견 충돌 시, 명확한 근거를 제시하고 합리적으로 설득하기.
6. 감정적 문제 발생 시, 즉시 해결하고 원만한 관계 유지하기.
7. 개인 일정을 팀원과 사전에 공유하기.
8. 코드 변경 시, 반드시 팀원에게 알리고 협의하기.
9. 눈치 보지 않고, 팀원 모두가 동등하게 의견 개진하기.

## 🎯 목표
- 요구사항을 충실히 구현하고,
- 프로젝트를 설명할 수 있으며,
- 포트폴리오로 활용할 수 있는 품질의 프로젝트 완성하기.

## 🤝 우리들의 약속

<details>
<summary>1. 코딩 컨벤션</summary>

### 패키지 구조
- src
  - java
    - domain
      - user
        - controller
        - service
          - impl
        - mapper
          - dto
          - entity
        - dto
          - controller
          - service
        - repository
        - entity
      - recipe
      - review
        ...이하 생략...
    - global
    - sample

### Naming

- **축약어 최소화**:
  - 가능한 한 축약어 사용을 피하고, 전체 단어를 사용합니다.
  - 심지어 100자를 넘어가도 괜찮습니다.

- **클래스 명은 파스칼 케이스**:
  - 첫 글자는 대문자로 시작하고, 각 단어의 첫 글자도 대문자로 합니다.
  - 약어도 첫 글자를 소문자로 씁니다.
    - 예) `URL → Url`
  - 예) `public class BoardController {…}`

- **필드 명, 메소드 명은 카멜 케이스**:
  - 첫 글자는 소문자로 시작하고, 그 뒤 각 단어의 첫 글자는 대문자로 합니다.
  - 약어도 첫 글자를 소문자로 씁니다.
    - 예) `URL → Url`
  - 예) `private String imageUrl;`

- **필드는 명사로 시작**:
  - 필드는 객체의 상태를 나타내므로 명사를 사용합니다.

- **메소드는 동사로 시작**:
  - 메소드는 객체의 행동을 나타내므로 동사를 사용합니다.
  - 끝은 명사로 맺어도 됩니다.

### Package

- **소문자 사용**:
  - 패키지 명은 모두 소문자로 작성합니다.

- **띄어쓰기 금지**:
  - 패키지 명에는 띄어쓰기를 사용하지 않습니다.

- **영어 소문자와 숫자만 사용**:
  - 특수문자 사용은 금지합니다.
    - 예) 잘못된 예시: `org.springeel.config_jpa`
  - 올바른 예시: `org.springeel.config.jpa2`

### Annotation

- **Lombok Annotation은 맨 위에**:
  - Lombok 관련 어노테이션은 다른 어노테이션보다 상단에 위치합니다.

- **길이가 짧은 어노테이션부터 작성**:
  - 옵션을 제외한 어노테이션의 길이가 짧은 순으로 작성합니다.

- **올바른 예시**:
    ```java
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Table(name = "TB_BOARD")
    @Entity
    public class Board {}
    ```

    ```java
    @RequiredArgsConstructor
    @Slf4j
    @RequestMapping("/api/v1")
    @RestController
    public class Controller {}
    ```
### Method

- **인자 값에 `final` 사용**:
  - 메소드의 인자 값들은 `final`로 설정합니다.
  - 변경이 필요한 경우에는 `final`을 사용하지 않습니다.

- **인자마다 개행 처리**:
  - 메소드 인자마다 개행 처리를 해서 가독성을 높입니다.

- **소괄호와 중괄호 위치**:
  - 메소드의 인자 부분에서 닫는 소괄호와 여는 중괄호는 다음 줄에 놓습니다.

- **올바른 예시**:
    ```java
    @Builder
    public User(
        final String email,
        final String password,
        final String nickname,
        final UserRole role
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
    ```
### Entity

- **엔티티 필드값 수정 방법**:
  - 엔티티의 필드값 수정은 엔티티 클래스 내부에서 메소드를 통해 진행합니다.
  - 수정 작업 시 `@Transactional` 어노테이션을 사용합니다.

- **연관관계 정의 위치**:
  - 연관관계는 필드 중 가장 아래에 정의합니다.
  - 양방향 연관관계의 경우, 연관관계 정의 중에서도 맨 마지막에 위치시킵니다.

- **생성자에 `@Builder` 사용**:
  - 생성자 레벨에서 `@Builder` 어노테이션을 사용합니다.
  - `id` 필드는 `@Builder`에 포함시키지 않습니다.

- **올바른 예시**:
    ```java
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Table(name = "TB_BOARD")
    @Entity
    public class Board {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        private String name;

        @Builder
        public Board(String name){
            this.name = name;
        }
    }
    ```
### DTO

- **`record` 사용**:
  - DTO 정의 시 `record`를 사용하여 간결하게 표현합니다.

- **명명 규칙**:
  - DTO의 이름은 목적/행위와 `{request/response}`를 포함하여 명명합니다.
  - 예) `BoardCreateRequestDto`, `BoardCreateResponseDto`

- **Controller와 Service 전용 DTO 분리**:
  - Controller 전용 DTO와 Service 전용 DTO를 분리하여 작성합니다.

- **올바른 예시**:
    ```java
    @PostMapping
    public void create(
        @RequestBody TestCreateControllerRequestDto controllerRequestDto
    ) {
        TestCreateServiceRequestDto serviceRequestDto =
            TestMapper.INSTANCE.toTestServiceRequestDto(controllerRequestDto);
        testService.create(serviceRequestDto);
    }
    ```
### Push 하기 전에 해야 될 일

- **코드 포맷팅**:
  - 코드를 커밋하기 전에 포맷팅을 수행합니다.
  - IntelliJ에서는 `ctrl + alt + l` (코드 포맷팅)과 `ctrl + alt + o` (불필요한 import 제거) 단축키를 사용합니다.
</details>

<details>
<summary>2. 깃 컨벤션</summary>

## GitHub 규칙

- **하나의 커밋에는 하나의 작업만**:
  - 커밋 시, 각 커밋에는 하나의 작업만 포함시킵니다. ⭐⭐⭐⭐⭐

- **코드 포맷팅**:
  - push를 하기 전에 항상 코드 포맷팅을 수행합니다. ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐

- **`develop` 브랜치의 최신 상태 유지**:
  - push 전에 `develop` 브랜치의 내용을 `pull`하거나 `merge`하여 최신 상태를 유지합니다. ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐

- **`application.yml` 파일 변경 금지**:
  - `application.yml` 파일은 변경하지 않습니다.

- **개발 환경 설정 파일 (`application-dev.yml`) 사용**:
  - 개인 설정은 `application-dev.yml` 파일에 추가하여 사용합니다.

## Push 하기 전에 해야 되는 작업 ⭐⭐⭐

💡 **remote origin이 딸깍! 레시피 레포지토리를 가리킨다고 가정한 상황입니다!**

### GitHub의 원격 `develop` 브랜치 내용을 현재 브랜치에 바로 `merge`하는 방법
1. 현재 브랜치에서 최종 `git commit` 진행
- 만약, 커밋을 못하는 상황이라면 `git stash`로 현재 작업 내용 임시 저장
2. `git pull origin develop` 실행 (develop 업데이트 시 수시로 진행 권장)
3. 현재 브랜치에서 `원격 develop`의 내용 추가 확인
4. 충돌 발생 시 해결 후 `git push origin (현재 브랜치 명)`
- `git stash`로 임시 저장한 경우, `git stash apply`로 불러오기

### GitHub의 원격 `develop` 브랜치 내용을 `로컬 브랜치 develop`에 옮겨서 `merge`하는 방법
1. 현재 브랜치에서 최종 `git commit` 진행
- 커밋 못하는 상황이면 `git stash`로 현재 작업 내용 임시 저장
2. `git switch develop`으로 `로컬 develop` 이동
3. `git pull origin develop`으로 원격 develop 내용 가져오기
4. 작업 중인 브랜치로 `git switch (작업중인 브랜치)` 이동
5. `git merge develop` 실행
6. 충돌 발생 시 해결 후 `git push origin (현재 브랜치 명)`
- `git stash`로 임시 저장한 경우, `git stash apply`로 불러오기

## 깃허브 사용 순서

1. **Issue 생성**:
- 하나의 이슈에는 여러 개의 서브 이슈가 있을 수 있습니다.
- `Assignees`와 `Labels` 등을 연결시켜 가독성을 높입니다.

2. **Issue를 Projects와 연결**:
- 처음 만들어진 이슈는 `Projects`의 `TODO`에 배치합니다.

3. **브랜치 생성 및 작업**:
- 작업을 위한 브랜치를 생성하고 해당 브랜치에서 작업을 진행합니다.

4. **Commit 시 Issue 넘버 포함**:
- 커밋 시, 관련된 이슈 번호를 포함하여 코멘트를 남깁니다.
- 예) `[#12]Feat: 로그인 기능 구현`

5. **PR(풀 리퀘스트) 작성**:
- PR 제목은 `브랜치 명 - 작업 내용`으로 작성합니다.
- 예) `Feature/swagger - Swagger 의존성 추가`

6. **코드 리뷰 수행**:
- 작성한 코드에 대해 리뷰를 받습니다.

7. **Merge 수행**:
- 리뷰 후 문제가 없다면 코드를 `merge`합니다.

## 깃허브 커밋 컨벤션

💡 **이슈 넘버도 커밋 메시지에 포함시킵시다! (없으면 생략 가능)**
예) `[#3]Feat: 로그인 기능 추가`

| 태그 이름 | 설명                               |
| ------- |----------------------------------|
| Feat    | 새로운 기능을 추가한 경우                   |
| Fix     | 버그를 고친 경우                        |
| Design  | CSS 등 사용자 UI 디자인 변경              |
| !BREAKING CHANGE | 기존 API 변경의 경우                    |
| !HOTFIX | 긴급하게 임시적인 버그를 고치는 경우             |
| Style   | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우 |
| Refactor | 프로덕션 코드 리팩토링 |
| Comment | 필요한 주석 추가 및 변경 |
| Docs | 문서를 수정한 경우 |
| Test | 테스트 추가, 테스트 리팩토링 (프로덕션 코드 변경 없음) |
| Chore | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우 (프로덕션 코드 변경 없음) |
| Rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 |
| Remove | 파일을 삭제하는 작업만 수행한 경우 |

## 브랜치 전략

- **`main`**:
  - 배포 가능한 버전을 관리합니다.

- **`develop`**:
  - 개발 중인 버전을 관리합니다.

- **`feature/(도메인)/(기능 명)`**:
  - 하나의 기능을 구현하는 브랜치입니다.
  - 예시:
    - `feature/review/create`
    - `feature/review/read`
    - `feature/review/update`
    - `feature/review/delete`

- **`(다른 작업)/(도메인 or 작업 단위)/(작업 명)`**:
  - 이외의 다른 작업을 분류하는 브랜치입니다.
  - 예시:
    - `sample`
    - `github/issue/template`
    - `refactor/config`
    - `refactor/review`
    - `refactor/review/read -style/review`

## Issues & PR 전략

- 이슈 및 PR 생성 시, 만들어진 템플릿을 활용합니다.
- PR을 생성할 때, 제목은 다음과 같은 형식으로 작성합니다: `브랜치명 - 작업 내용`.
  - 이슈가 연관되어 있다면 해당 이슈를 언급할 수 있습니다.
- PR 내용에는 "관련 이슈" 항목을 포함하며, `close #(이슈 넘버)`를 기입하면 PR이 merge될 때 해당 이슈가 자동으로 닫힙니다.
  - 여러 개의 이슈를 등록할 수 있으며, 예시: `close #13 #15`.
</details>

<details>
<summary>3. 커뮤니케이션 채널</summary>

- **젭 (Zoom):** 주로 화상 회의 및 원격 작업을 위해 사용합니다. 회의 일정은 미리 공유되며, 중요한 토론과 업무 리뷰에 활용됩니다.

- **슬랙 (Slack):** 팀 간 실시간 채팅 및 업무 관련 대화에 사용됩니다. 채널은 프로젝트 및 토픽별로 구성되어 있으며, 업무 업데이트와 의사 결정을 공유하는 데 활용됩니다.

- **디스코드 (Discord):** 무료 음성 및 텍스트 채팅 플랫폼으로, 비공식적인 소셜 채팅 및 일상적인 대화에 사용됩니다.

- **카카오톡 (KakaoTalk):** 팀 내에서 빠른 메시지 송수신 및 간단한 토론에 활용됩니다. 주로 긴급한 사항이나 간단한 안내에 사용됩니다.

- **깃허브 (GitHub):** 코드 관리 및 협업 플랫폼으로, 소스 코드 관리, 이슈 트래킹, 코드 리뷰 및 협력에 사용됩니다. PR과 코드 변경 사항을 중심으로 작업합니다.

각 채널의 사용 목적과 특징에 따라 적절하게 선택하여 효율적인 커뮤니케이션을 유지하고 있습니다.
</details>

## 🛠 기술 스택
- **Frontend**
    - Svelte 4.2.8
    - Svelte Material UI 7.0.0-beta.16
- **Backend**
    - Java 21
    - Spring boot 3.2.1
    - OAuth
    - Swagger 2.3.0
- **Database**
    - MySQL 8.0.35
- **Infrastructure**
    - AWS EC2, S3, RDS, Route53, ACM, ELB
    - Nginx
    - Docker
    - Gradle
- **CI/CD**
    - Gradle
    - GitHub Actions
    - Netlify 배포

## ERD
![ERD2](https://github.com/phdljr/one-click-recipe/assets/141345981/b1983b67-a702-4445-b687-bf6cf890b065)

## 아키텍쳐
![Untitled (4)](https://github.com/phdljr/one-click-recipe/assets/141345981/66ec7a73-c414-431b-a0cc-865cbc8054f1)

## 🗣️기술적 의사결정

+ [식재료 활용 방안](https://github.com/phdljr/one-click-recipe/wiki/%EC%8B%9D%EC%9E%AC%EB%A3%8C-%EB%8D%B0%EC%9D%B4%ED%84%B0-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EC%95%88)

+ [버튼 클릭 시, 장바구니에 식재료를 담는 방식](https://github.com/phdljr/one-click-recipe/wiki/%EB%B2%84%ED%8A%BC-%ED%81%B4%EB%A6%AD-%EC%8B%9C,-%EC%9E%A5%EB%B0%94%EA%B5%AC%EB%8B%88%EC%97%90-%EC%8B%9D%EC%9E%AC%EB%A3%8C%EB%A5%BC-%EB%8B%B4%EB%8A%94-%EB%B0%A9%EC%8B%9D)

## 🛠 트러블슈팅

+ [HTTP & HTTPS 혼합 컨텐츠(Mixed Content) 차단](https://github.com/phdljr/one-click-recipe/wiki/HTTP-&-HTTPS-%ED%98%BC%ED%95%A9-%EC%BB%A8%ED%85%90%EC%B8%A0(Mixed-Content)-%EC%B0%A8%EB%8B%A8)

+ [Recipe 엔티티 생성 시, 연관된 엔티티도 같이 생성하는 방식 채택](https://github.com/phdljr/one-click-recipe/wiki/Recipe-%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%83%9D%EC%84%B1-%EC%8B%9C,-%EC%97%B0%EA%B4%80%EB%90%9C-%EC%97%94%ED%8B%B0%ED%8B%B0%EB%8F%84-%EA%B0%99%EC%9D%B4-%EC%83%9D%EC%84%B1%ED%95%98%EB%8A%94-%EB%B0%A9%EC%8B%9D-%EC%B1%84%ED%83%9D)

+ [SSH를 통한 CD 구축할 시, 가끔씩 실패하는 경우 발생](https://github.com/phdljr/one-click-recipe/wiki/SSH%EB%A5%BC-%ED%86%B5%ED%95%9C-CD-%EA%B5%AC%EC%B6%95%ED%95%A0-%EC%8B%9C,-%EA%B0%80%EB%81%94%EC%94%A9-%EC%8B%A4%ED%8C%A8%ED%95%98%EB%8A%94-%EA%B2%BD%EC%9A%B0-%EB%B0%9C%EC%83%9D)

+ [SpringBoot 3.2.1 에서의 @PathVariable 파라미터 인식 오류](https://github.com/phdljr/one-click-recipe/wiki/SpringBoot-3.2.1-%EC%97%90%EC%84%9C%EC%9D%98-@PathVariable-%ED%8C%8C%EB%9D%BC%EB%AF%B8%ED%84%B0-%EC%9D%B8%EC%8B%9D-%EC%98%A4%EB%A5%98)

+ [좋아요 조회 기능을 레시피 조회 API에 병합](https://github.com/phdljr/one-click-recipe/wiki/%EC%A2%8B%EC%95%84%EC%9A%94-%EC%A1%B0%ED%9A%8C-%EA%B8%B0%EB%8A%A5%EC%9D%84-%EB%A0%88%EC%8B%9C%ED%94%BC-%EC%A1%B0%ED%9A%8C-API%EC%97%90-%EB%B3%91%ED%95%A9)

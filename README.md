# kotlin-spring-study

<br>

## 학습 목표

- Kotlin 의 기본적인 문법과 동작 방식(Coroutine)을 익힙니다.

- Kotlin 기반의 Spring Boot 와 익숙해집니다.

- (추가 목표) 본인만의 코드 스타일 만들어보기.

- (추가 목표) Kotlin 기반의 Spring 에서 DDD 도입해보기.

<br>

## 진행 방식

> NOTE:
>
> 매주 수요일 오후 2시 전까지 꼭! Pull Request 을 올려야합니다.

- 매주 **수요일 오후 3시** 정기 디스코드 질의응답 시간

- 인프런의 [<실전! 코틀린과 스프링 부트로 도서관리 애플리케이션 개발하기>](https://www.inflearn.com/course/java-to-kotlin-2) 강의를 듣고 실습을 진행합니다.

- 실습 후, 본인만의 코드 스타일로 리팩토링까지가 이번주 스터디의 목표입니다.

- 실습 또는 리팩토링 완료 후 Pull Request 를 올려 스터디원들이 코드 리뷰 및 간단한 질의 응답을 진행합니다.

<br>

## 브랜치 규칙

> **NOTE:**
>
> 리포지토리를 Fork 후, 진행해주세요.<br>
> 또한, main 브랜치에는 `README.md` 파일만 존재하도록 합니다.

`main` 브랜치에서 진행하는 것이 아닌, 본인 닉네임으로 브랜치를 만들고 **해당 브랜치**에서 진행합니다.

아래는 브랜치 규칙에 대한 예시입니다:

```bash
# Example by hong

## 자기 닉네임(ex. hong)으로 브랜치를 생성합니다.
git branch hong

## 생성한 브랜치로 들어갑니다.
git checkout hong

## 이제, 해당 브랜치에서 실습 및 리팩토링을 진행하고
## 완료 후 본인 브랜치로 push 및 PR을 올립니다.
git add .
git commit -m "chore: test"
git push origin hong
```

<br>

## 커밋 규칙

프로젝트를 진행하는 것이 아닌, 스터디를 진행하는 것이므로 **자유 형식**으로 진행합니다.

만약, 커밋 형식을 맞추고 싶다면 아래의 **커밋 컨벤션**을 따라주세요:

```bash
# 커밋 컨벤션
<type>: <subject>

## <type> Rules
`feat` - 기능 추가
`fix` - 버그 수정
`docs` - 문서 수정
`refactor` - 리팩토링
`remove` - 파일 및 디렉토리 삭제
`rename` - 파일 및 디렉토리 명 수정
`chore` - production code 와 무관한 작업들

## <subject>
제목은 영문으로 최대 52자를 넘기지 않습니다.
동사원형을 가장 앞에 작성합니다.
마침표와 같은 특수기호는 사용하지 않습니다.

# Example

# example 1
feat: Add member entity and service

# example 2
chore: Update properties to configuration flyway
```

<br>

## 스터디원

<table style="text-align: center;">
    <tr>
        <td>hong.kim (김지홍)</td>
        <td>jun.park (박준서)</td>
        <td>eric.ha (하남규)</td>
        <td>kanuda.hyun (현도훈)</td>
    </tr>
    <tr>
        <td><a href="https://github.com/JiHongKim98"><img src="https://avatars.githubusercontent.com/u/144337839?v=4" /></a></td>
        <td><a href="https://github.com/junseoparkk"><img src="https://avatars.githubusercontent.com/u/98972385?v=4"/></a></td>
        <td><a href="https://github.com/Namgyu11"><img src="https://avatars.githubusercontent.com/u/103015031?v=4" /></a></td>
        <td><a href="https://github.com/DohunHyun"><img src="https://avatars.githubusercontent.com/u/33439924?v=4" /></a></td>
    </tr>
    <tr>
        <td><a href="https://github.com/god-of-kotlin/kotlin-spring-study/tree/hong">hong branch</a></td>
        <td><a href="https://github.com/god-of-kotlin/kotlin-spring-study/tree/jun">jun branch</a></td>
        <td><a href="https://github.com/god-of-kotlin/kotlin-spring-study/tree/eric">eric branch</a></td>
        <td><a href="https://github.com/god-of-kotlin/kotlin-spring-study/tree/kanuda">kanuda branch</a></td>
    </tr>
</table>

<br>

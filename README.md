# Stack Overflow clone

### 프로젝트 소개

세계적으로 유명한 개발자들의 커뮤니티, Stack Overflow를 clone한 프로젝트입니다.

👇 아래 링크를 통해 저희들의 Stack Overflow를 체험해보세요 :)
[Stack Overflow create by.pre038](http://pre-project-038-client.s3-website.ap-northeast-2.amazonaws.com)

### 구현 기능

**1. 회원가입 및 로그인/로그아웃**

- 닉네임, 이메일, 비밀번호 입력을 통해 회원가입할 수 있습니다.
- 중복된 닉네임 또는 이메일을 사용하려 회원가입 시도할 경우 모달 창을 통해 사용할 수 없다는 안내가 나옵니다.
- 회원가입 후 이메일과 비밀번호를 통해 로그인할 수 있습니다.
- 오른쪽 상단에 있는 말풍선>로그아웃 버튼을 통해 로그아웃할 수 있습니다.

**2. Question Create/Read/Delete**

- Home에서 Top Questions을, Questions에서 All Questions을 볼 수 있습니다.
- 질문의 제목을 클릭하여 질문 상세 페이지로 이동할 수 있습니다.
- 로그인 한 사용자만 질문을 등록할 수 있습니다.
- 질문 등록시, 정해진 글자 수 이상(본문 20글자 이상) 작성하여야 등록할 수 있습니다.
- 자신이 등록한 질문만 삭제할 수 있습니다.

**3. Answer Create/Read/Delete**

- 질문 상세 페이지에서 각 질문에 대한 답변을 확인할 수 있습니다.
- 로그인 한 사용자만 답변을 등록할 수 있습니다.
- 답변 등록시, 정해진 글자 수 이상(본문 20글자 이상) 작성하여야 등록할 수 있습니다.
- 자신이 등록한 답변만 삭제할 수 있습니다.

**4. Comment Create/Read/Delete**

- 질문 상세 페이지에서 각 질문과 답변에 대한 댓글을 확인할 수 있습니다.
- 로그인 한 사용자만 댓글을 등록할 수 있습니다.
- 자신이 등록한 댓글만 삭제할 수 있습니다.

**5. Search bar 검색 기능**

- Header 에 있는 Searchbar 를 통해 질문을 검색할 수 있습니다.
- 검색 결과는 키워드가 제목 혹은 본문에 포함된 질문들만 filter 되어 화면에 출력됩니다.

**6. Vote 기능**

- 로그인 한 사용자만 투표를 할 수 있습니다.
- 모든 질문과 답변에 한 아이디당 단 한 번만 투표가 가능합니다.
- upvote 와 downvote 수가 즉시 합산되어 숫자로 화면에 출력됩니다.

### 🌟Team Members

<table>
  <tbody>
    <tr>
    <td align="center"><a href="https://github.com/serin-B"><img src="https://avatars.githubusercontent.com/u/107970881?v=4" width="100px;" alt="avatar image"/><br /><sub><b>Serin Bang</b></sub></a><br /><a href="https://github.com/codestates-seb/seb40_pre_038/commits?author=serin-B" title="Documentation">🪄FE</a></td>
    <td align="center"><a href="https://github.com/rosenfence"><img src="https://avatars.githubusercontent.com/u/90300215?v=4" width="100px;" alt="avatar image"/><br /><sub><b>Changha Shin</b></sub></a><br /><a href="https://github.com/codestates-seb/seb40_pre_038/commits?author=rosenfence" title="Documentation">🪄FE</a></td>
    <td align="center"><a href="https://github.com/jwo0o0"><img src="https://avatars.githubusercontent.com/u/70098708?v=4" width="100px;" alt="avatar image"/><br /><sub><b>Jeongwoo Kim</b></sub></a><br /><a href="https://github.com/codestates-seb/seb40_pre_038/commits?author=jwo0o0" title="Documentation">🪄FE</a></td>
    <td align="center"><a href="https://github.com/JinhuiKim"><img src="https://avatars.githubusercontent.com/u/20276678?v=4" width="100px;" alt="avatar image"/><br /><sub><b>Jinhui Kim</b></sub></a><br /><a href="https://github.com/codestates-seb/seb40_pre_038/commits?author=JinhuiKim" title="Documentation">🪄FE</a></td>
    </tr>
    <tr>
    <td align="center"><a href="https://github.com/JadeMK"><img src="https://avatars.githubusercontent.com/u/97623334?v=4" width="100px;" alt="avatar image"/><br /><sub><b>Min Joo Kim</b></sub></a><br /><a href="https://github.com/codestates-seb/seb40_pre_038/commits?author=JadeMK" title="Documentation">🪄BE</a></td>
    <td align="center"><a href="https://github.com/gnidinger"><img src="https://avatars.githubusercontent.com/u/13742045?v=4" width="100px;" alt="avatar image"/><br /><sub><b>Taeyeong Kim</b></sub></a><br /><a href="https://github.com/codestates-seb/seb40_pre_038/commits?author=gnidinger" title="Documentation">🪄BE</a></td>
    <td align="center"><a href="https://github.com/hayoung10"><img src="https://avatars.githubusercontent.com/u/39071652?v=4" width="100px;" alt="avatar image"/><br /><sub><b>Hayoung Moon</b></sub></a><br /><a href="https://github.com/codestates-seb/seb40_pre_038/commits?author=hayoung10" title="Documentation">🪄BE</a></td>
    </tr>
  </tbody>
</table>

### 기술 스택

<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> ![Redux](https://img.shields.io/badge/redux-%23593d88.svg?style=for-the-badge&logo=redux&logoColor=white) ![Styled Components](https://img.shields.io/badge/styled--components-DB7093?style=for-the-badge&logo=styled-components&logoColor=white)

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

### 배운점

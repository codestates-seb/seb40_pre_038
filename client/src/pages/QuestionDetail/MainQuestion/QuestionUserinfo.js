import styled from 'styled-components';

const QuestionUserinfoContainer = styled.div`
  width: 200px;
  height: 67px;
  padding: 5px 6px 7px 7px;
  display: flex;
  flex-direction: column;
  background-color: #d9eaf7;
  border-radius: 4px;
`;

const QuestionDate = styled.div`
  width: 100%;
  font-size: 12px;
  color: #6a737c;
`;

const UserInfoWrapper = styled.div`
  display: flex;
  margin-top: 4px;
`;

const UserIcon = styled.div`
  img {
    width: 32px;
    height: 32px;
  }
`;

const UserStats = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 6px;
  .username {
    font-size: 13px;
    color: #0074cc;
  }
  .stats {
    font-size: 12px;
    color: #6a737c;
  }
`;

//이것도 axios로 요청??? Question created_time 사용
const AskedDate = ' Dec 28, 2014 at 14:19';
const UserName = 'jwo0o0';

const QuestionUserinfo = () => {
  return (
    <QuestionUserinfoContainer>
      <QuestionDate>asked {AskedDate}</QuestionDate>
      <UserInfoWrapper>
        <UserIcon>
          <img
            src={process.env.PUBLIC_URL + '/images/user_icon_2.png'}
            alt="user profile icon"
          ></img>
        </UserIcon>
        <UserStats>
          <div className="username">{UserName}</div>
          <div className="stats">1,201 •2 •9 •6</div>
        </UserStats>
      </UserInfoWrapper>
    </QuestionUserinfoContainer>
  );
};

export default QuestionUserinfo;

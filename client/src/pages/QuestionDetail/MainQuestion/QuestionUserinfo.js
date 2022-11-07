import styled from 'styled-components';

const QuestionUserinfoContainer = styled.div`
  width: 200px;
  height: 67px;
  padding: 5px 6px 7px 7px;
  display: flex;
  flex-direction: column;
  background-color: #d9eaf7;
  border-radius: 4px;
  &.answer {
    background-color: white;
  }
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

const AskedDate = (str) => {
  if (str === '') return '';
  const date = new Date(str.slice(0, 19));
  const arr = date.toDateString().split(' ');

  return `${arr[1]} ${arr[2]}, ${arr[3]} at ${String(date.getHours()).padStart(
    2,
    '0'
  )}:${String(date.getMinutes()).padStart(2, '0')}`;
};

const QuestionUserinfo = ({ type, userName, createdAt, reputation }) => {
  const rep = reputation < 10000 ? reputation : Math.floor(reputation / 1000);
  const random = () => {
    return Math.floor(Math.random() * 101);
  };

  return (
    <QuestionUserinfoContainer className={type === 'answer' ? 'answer' : null}>
      <QuestionDate>asked {AskedDate(createdAt)}</QuestionDate>
      <UserInfoWrapper>
        <UserIcon>
          <img
            src={process.env.PUBLIC_URL + '/images/user_icon_2.png'}
            alt="user profile icon"
          ></img>
        </UserIcon>
        <UserStats>
          <div className="username">{userName}</div>
          <div className="stats">
            {`${rep} •${random()} •${random()} •${random()}`}
          </div>
        </UserStats>
      </UserInfoWrapper>
    </QuestionUserinfoContainer>
  );
};

export default QuestionUserinfo;

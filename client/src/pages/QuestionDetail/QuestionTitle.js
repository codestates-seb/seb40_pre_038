import styled from 'styled-components';
import { ButtonBlue } from '../../components/Buttons';

const QuestionTitleContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
`;

const TitleInfo = styled.h1`
  display: flex;
  font-size: 27px;
  font-weight: 400;
  text-align: left;
  flex-grow: 1;
  justify-content: space-between;
  color: #3b4045;
  margin: 0;
  div {
    margin-right: 12px;
    white-space: normal;
  }
`;

const AskQustionBtn = styled(ButtonBlue)``;

const StatInfo = styled.div`
  display: flex;
  align-items: center;
  flex-grow: 1;
  height: 34px;
  padding-bottom: 8px;
  margin-bottom: 16px;
  font-size: 13px;
  border-bottom: 1px solid #e3e6e8;
  .name {
    color: #6a737c;
    margin-right: 5px;
  }
  .stat {
    color: #232629;
    margin-right: 16px;
  }
`;

const QuestionTitle = () => {
  const title = 'Java 8, Streams to find the duplicate elements';
  return (
    <QuestionTitleContainer>
      <TitleInfo>
        <div>{title}</div>
        <div>
          <AskQustionBtn
            width="100px"
            height="37px"
            fontSize="13px"
            fontWeight="400"
          >
            Ask Question
          </AskQustionBtn>
        </div>
      </TitleInfo>
      <StatInfo>
        <div className="name">Asked</div>
        <div className="stat">7 years, 10 months ago</div>
        <div className="name">Modified</div>
        <div className="stat">today</div>
        <div className="name">Viewd</div>
        <div className="stat">213K times</div>
      </StatInfo>
    </QuestionTitleContainer>
  );
};

export default QuestionTitle;

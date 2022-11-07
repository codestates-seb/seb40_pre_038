import styled from 'styled-components';
import QuestionBodyTxt from './QuestionBodyTxt';
import QuestionTags from './QuestionTags';
import QuestionBodyBtns from './QuestionBodyBtns';
import QuestionUserinfo from './QuestionUserinfo';

const QuestionBodyContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const QuestionBodyBottom = styled.div`
  width: 100%;
  display: flex;
  margin-top: 30px;
  justify-content: space-between;
`;

const QuestionBody = ({ data, type, answerId }) => {
  const problem = Object.keys(data).length === 0 ? '' : data.problem;
  const expect = Object.keys(data).length === 0 ? '' : data.expect;
  const tagList = Object.keys(data).length === 0 ? [] : data.tagList;
  const userName =
    Object.keys(data).length === 0 ? '' : data.userInformation.nickName;
  const createdDate = Object.keys(data).length === 0 ? '' : data.createdAt;
  const answerbody = Object.keys(data).length === 0 ? '' : data.body;
  const reputation =
    Object.keys(data).length === 0 ? 0 : data.userInformation.reputation;

  return (
    <QuestionBodyContainer>
      {type === 'question' ? (
        <>
          <QuestionBodyTxt text={problem} />
          <QuestionBodyTxt text={expect} />
          <QuestionTags tagList={tagList} />
        </>
      ) : (
        <QuestionBodyTxt text={answerbody} />
      )}

      <QuestionBodyBottom>
        <QuestionBodyBtns type={type} answerId={answerId} />
        <QuestionUserinfo
          type={type}
          userName={userName}
          createdAt={createdDate}
          reputation={reputation}
        />
      </QuestionBodyBottom>
    </QuestionBodyContainer>
  );
};

export default QuestionBody;

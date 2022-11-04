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

const QuestionBody = ({ data, type }) => {
  const problem = Object.keys(data).length === 0 ? '' : data.problem;
  const expect = Object.keys(data).length === 0 ? '' : data.expect;
  const tagList = Object.keys(data).length === 0 ? [] : data.tagList;
  let userName = '';
  if (Object.keys(data).length !== 0 && type === 'question') {
    userName = data.userInformation.nickName;
  } else if (Object.keys(data).length !== 0 && type === 'answer') {
    userName = data.userResponseDto.nickName;
  }
  const createdDate = Object.keys(data).length === 0 ? '' : data.createdAt;
  const answerbody = Object.keys(data).length === 0 ? '' : data.body;

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
        <QuestionBodyBtns type={type} />
        <QuestionUserinfo
          type={type}
          userName={userName}
          createdAt={createdDate}
        />
      </QuestionBodyBottom>
    </QuestionBodyContainer>
  );
};

export default QuestionBody;

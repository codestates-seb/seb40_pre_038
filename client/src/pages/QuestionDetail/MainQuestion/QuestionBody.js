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
  console.log(data, type);
  const problem = data === undefined ? '' : data.problem;
  const expect = data === undefined ? '' : data.expect;
  const tagList = data === undefined ? [] : data.tagList;
  let userName = '';
  if (data !== undefined && type === 'question') {
    userName = data.userInformation.nickName;
  } else if (data !== undefined && type === 'answer') {
    userName = data.userResponseDto.nickName;
  }
  const createdDate = data === undefined ? '' : data.createdAt;
  const answerbody = data === undefined ? '' : data.body;

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
        <QuestionBodyBtns />
        <QuestionUserinfo userName={userName} createdAt={createdDate} />
      </QuestionBodyBottom>
    </QuestionBodyContainer>
  );
};

export default QuestionBody;

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
  justify-content: space-between;
`;

const QuestionBody = ({ data }) => {
  const problem = data === undefined ? '' : data.problem;
  const expect = data === undefined ? '' : data.expect;
  const tagList = data === undefined ? [] : data.tagList;
  const userName = data === undefined ? '' : data.userResponseDto.nickName;
  const createdDate = data === undefined ? '' : data.createdAt;

  return (
    <QuestionBodyContainer>
      <QuestionBodyTxt text={problem} />
      <QuestionBodyTxt text={expect} />
      <QuestionTags tagList={tagList} />
      <QuestionBodyBottom>
        <QuestionBodyBtns />
        <QuestionUserinfo userName={userName} createdAt={createdDate} />
      </QuestionBodyBottom>
    </QuestionBodyContainer>
  );
};

export default QuestionBody;

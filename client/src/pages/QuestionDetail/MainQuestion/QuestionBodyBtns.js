import styled from 'styled-components';

const QuestionBodyBtnsContainer = styled.div`
  display: flex;
  width: 247px;
`;

const QuestionBtn = styled.button`
  background-color: transparent;
  font-size: 13px;
  color: #6a737c;
  height: 15px;
  margin: 2px;
  border: none;
  &:hover {
    cursor: pointer;
    color: #838c95;
  }
`;
const ShareBtn = styled(QuestionBtn)``;
const EditBtn = styled(QuestionBtn)``;
const FollowBtn = styled(QuestionBtn)``;

const QuestionBodyBtns = () => {
  return (
    <QuestionBodyBtnsContainer>
      <ShareBtn>Share</ShareBtn>
      <EditBtn>Edit</EditBtn>
      <FollowBtn>Follow</FollowBtn>
    </QuestionBodyBtnsContainer>
  );
};

export default QuestionBodyBtns;

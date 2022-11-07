import styled from 'styled-components';
import { useDispatch, useSelector } from 'react-redux';
import { patchQuestionsVote } from '../../_actions/vote_action';

const QuestionVoteContainer = styled.div`
  width: 60px;
  height: 200px;
  padding-right: 12px;
  display: flex;
  flex-direction: column;
  align-items: center;
  fill: hsl(210, 8%, 75%);
`;

const VoteBtn = styled.button`
  width: 36px;
  height: 36px;
  background-color: transparent;
  border: none;
  padding: 0;
  &:hover {
    cursor: pointer;
  }
  &:active {
    fill: #f48225;
  }
`;
const UpVoteBtn = styled(VoteBtn)``;
const DownVoteBtn = styled(VoteBtn)``;

const VoteStat = styled.div`
  width: 36px;
  font-size: 21px;
  color: #6a7376;
  text-align: center;
`;

const SideIcon = styled.div`
  display: flex;
  justify-content: center;
  &:hover {
    cursor: pointer;
  }
`;
const SaveIcon = styled(SideIcon)`
  margin: 4px 0;
`;
const ActivityIcon = styled(SideIcon)`
  margin: 8px 0;
`;

const QuestionVote = ({ data }) => {
  const dispatch = useDispatch();
  const vote = useSelector((state) => {
    if (state.voteReducer.questionsVote === 0) {
      return state.questionReducer.data.vote;
    } else {
      return state.voteReducer.questionsVote;
    }
  });
  const questionId = data.questionId;

  const questionsUpVote = () => {
    const patchBody = {
      vote: vote + 1,
    };
    dispatch(patchQuestionsVote(questionId, patchBody));
  };

  const questionsDownVote = () => {
    const patchBody = {
      vote: vote - 1,
    };
    dispatch(patchQuestionsVote(questionId, patchBody));
  };

  return (
    <QuestionVoteContainer>
      <UpVoteBtn onClick={questionsUpVote}>
        <svg aria-hidden="true" width="36" height="36" viewBox="0 0 36 36">
          <path d="M2 25h32L18 9 2 25Z" />
        </svg>
      </UpVoteBtn>
      <VoteStat>{vote}</VoteStat>
      <DownVoteBtn onClick={questionsDownVote}>
        <svg aria-hidden="true" width="36" height="36" viewBox="0 0 36 36">
          <path d="M2 11h32L18 27 2 11Z" />
        </svg>
      </DownVoteBtn>
      <SaveIcon>
        <svg aria-hidden="true" width="18" height="18" viewBox="0 0 18 18">
          <path d="m9 10.6 4 2.66V3H5v10.26l4-2.66ZM3 17V3c0-1.1.9-2 2-2h8a2 2 0 0 1 2 2v14l-6-4-6 4Z" />
        </svg>
      </SaveIcon>
      <ActivityIcon>
        <svg aria-hidden="true" width="19" height="18" viewBox="0 0 19 18">
          <path d="M3 9a8 8 0 1 1 3.73 6.77L8.2 14.3A6 6 0 1 0 5 9l3.01-.01-4 4-4-4h3L3 9Zm7-4h1.01L11 9.36l3.22 2.1-.6.93L10 10V5Z" />
        </svg>
      </ActivityIcon>
    </QuestionVoteContainer>
  );
};

export default QuestionVote;

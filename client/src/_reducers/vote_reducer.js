import {
  PATCH_QUESTIONS_VOTE,
  PATCH_ANSWERS_VOTE,
} from '../_actions/vote_action';
const initialState = {
  questionsVote: 0,
  answersVote: 0,
};
export const voteReducer = (state = initialState, action) => {
  switch (action.type) {
    case PATCH_QUESTIONS_VOTE:
      return { ...state, questionsVote: action.payload };
    case PATCH_ANSWERS_VOTE:
      return { ...state, answersVote: action.payload };
    default:
      return state;
  }
};

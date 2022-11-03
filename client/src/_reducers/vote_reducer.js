import {
  PATCH_QUESTIONS_VOTE,
  PATCH_ANSWERS_VOTE,
} from '../_actions/vote_action';

export const voteReducer = (state, action) => {
  switch (action.type) {
    case PATCH_QUESTIONS_VOTE:
      return null;
    case PATCH_ANSWERS_VOTE:
      return null;
    default:
      return state;
  }
};

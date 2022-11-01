import { POST_QUESTION } from '../_actions/questions_action';

export default function questions(state = {}, action) {
  switch (action.type) {
    case POST_QUESTION:
      return { ...state, data: action.payload };
    default:
      return state;
  }
}

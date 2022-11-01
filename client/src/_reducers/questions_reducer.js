import { POST_NEW_QUESTION } from '../_actions/questions_action';

export default function questions(state = {}, action) {
  switch (action.type) {
    case POST_NEW_QUESTION:
      return null;
    default:
      return state;
  }
}

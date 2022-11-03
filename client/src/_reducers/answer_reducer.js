import { GET_ANSWERS, ADD_ANSWER } from '../_actions/answer_action';

const initialState = {
  data: [],
};
export const answerReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_ANSWERS:
      return { ...state, ...action.payload };
    case ADD_ANSWER:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

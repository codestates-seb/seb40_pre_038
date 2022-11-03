import {
  GET_ANSWERS,
  ADD_ANSWER,
  POST_ANSWER_COMMENT,
} from '../_actions/answer_action';

const initialState = {
  data: [],
};
export const answerReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_ANSWERS:
      return { ...state, ...action.payload };
    case ADD_ANSWER:
      return { ...state, ...action.payload };
    case POST_ANSWER_COMMENT:
      console.log(action.payload);
      return state;
    default:
      return state;
  }
};

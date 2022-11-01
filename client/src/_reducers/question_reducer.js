import { GET_QUESTION, SET_QUESTION_ID } from '../_actions/question_action';

const initialState = {
  question_id: 1,
  data: {},
};

export const questionReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_QUESTION_ID:
      return { ...state, question_id: action.payload };
    case GET_QUESTION:
      return { ...state, data: action.payload };
    default:
      return state;
  }
};

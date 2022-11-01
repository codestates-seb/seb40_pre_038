import { GET_ANSWERS } from '../_actions/answer_action';

const initialState = {
  data: {},
};
export const answerReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_ANSWERS:
      return { ...state, data: action.payload };
    default:
      return state;
  }
};

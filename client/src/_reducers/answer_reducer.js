import {
  GET_ANSWERS,
  ADD_ANSWER,
  POST_ANSWER_COMMENT,
} from '../_actions/answer_action';

const initialState = {
  data: [],
};
export const answerReducer = (state = initialState, action) => {
  let changeAnswer;
  switch (action.type) {
    case GET_ANSWERS:
      return { ...state, ...action.payload };
    case ADD_ANSWER:
      return { ...state, ...action.payload };
    case POST_ANSWER_COMMENT:
      console.log(action.payload);
      console.log(state.data);
      changeAnswer = state.data.filter((el) => {
        return el.answerId === action.payload.answer_id;
      });
      console.log(changeAnswer);
      changeAnswer[0].commentsWithUser.push(action.payload.data.data);
      return {
        ...state,
        ...state.data.map((el) => {
          if (el.answerId === action.payload.answer_id) return changeAnswer;
          else return el;
        }),
      };
    //return state;
    default:
      return state;
  }
};

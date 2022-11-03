import Comments from '../Comments/Comments';

const QuestionComments = ({ data, type }) => {
  const commentsData = data === undefined ? [] : data.commentsWithUser;
  return <Comments data={commentsData} type={type}></Comments>;
};

export default QuestionComments;

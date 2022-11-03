import Comments from '../Comments/Comments';

const QuestionComments = ({ data, type, answerId }) => {
  const commentsData =
    Object.keys(data).length === 0 ? [] : data.commentsWithUser;
  return (
    <Comments data={commentsData} type={type} answerId={answerId}></Comments>
  );
};

export default QuestionComments;

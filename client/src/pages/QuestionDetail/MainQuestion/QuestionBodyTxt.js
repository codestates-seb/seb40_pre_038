import styled from 'styled-components';

const QuestionBodyTxtContainer = styled.div`
  width: 100%;
  text-align: left;
  font-size: 15px;
  overflow-wrap: break-word;
  vertical-align: baseline;
  line-height: 22.5px;
  color: #232629;
`;

const QuestionBodyTxt = ({ text }) => {
  return <QuestionBodyTxtContainer>{text}</QuestionBodyTxtContainer>;
};

export default QuestionBodyTxt;

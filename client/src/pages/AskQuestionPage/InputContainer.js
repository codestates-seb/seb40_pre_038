import styled from 'styled-components';
import { ButtonBlue } from '../../components/Buttons';

const InputWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 20px;
  border: 1px solid #e3e6e8;
  border-radius: 3px;
  label.inputTitle {
    color: black;
    font-size: 15px;
    font-weight: 700;
  }
  label.inputDesc {
    color: #232629;
    font-size: 12px;
    margin: 5px 0px;
  }
  input::placeholder {
    color: #babfc4;
  }
  @media screen and (min-width: 1101px) {
    width: 800px;
    height: max-content;
  }
`;
export const InputContainer = ({ title, desc, children }) => {
  return (
    <InputWrapper>
      <label className="inputTitle">{title}</label>
      <label className="inputDesc">{desc}</label>
      {children}
      <div style={{ margin: '10px 0px 0px' }}>
        <ButtonBlue width="60px">Next</ButtonBlue>
      </div>
    </InputWrapper>
  );
};

const StyledInputContainer = styled.form`
  box-sizing: border-box;
  padding: 0 8px;
  position: relative;
  left: -10px;
`;
const StyledInput = styled.input`
  box-sizing: border-box;
  width: ${(props) => props.width || '756px'};
  height: ${(props) => props.height || '30px'};

  border: 1px solid #e3e6e8;
  border-radius: 3px;
  padding: 10px;

  &:focus {
    outline: none;
    border: 1px solid #6bbbf7;
    box-shadow: 0px 0px 0px 4px #d8e5f2;
  }
`;
export const StyledInputComponent = ({ width, height, placeholder }) => {
  return (
    <StyledInputContainer>
      <StyledInput
        width={width}
        height={height}
        type="text"
        placeholder={placeholder}
      ></StyledInput>
    </StyledInputContainer>
  );
};

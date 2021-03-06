import java.util.*;
import java.io.*;
import java.io.IOException;

class symbolTable
{
      Vector variableList;
      Variable newVariable;
      int variableCount;
      MyException UndefinedVariableException;
      MyException TypeCastException;

      symbolTable()
      {
                  variableList = new Vector();           
                  newVariable = new Variable();
                  UndefinedVariableException = new MyException("UndefinedVariableException");
                  TypeCastException = new MyException("TypeCastException");
      }

      public void addVariable(String varName, String varType, String varValue)
      {
             newVariable = new Variable(varType, varName, varValue);
             variableList.addElement(newVariable);
             variableCount++;
             UndefinedVariableException = new MyException("UndefinedVariableException");
             TypeCastException = new MyException("TypeCastException");
      }

      public int directAssignmentFromVarResult(String varType, String castType, String expressionType, String varName, String Expression) throws MyException
      {
             int errcode = 0;

             if(varType.compareTo("double") == 0)
             {
                addVariable(varName, varType, getStringValueOf(Expression));
             }

             else
             {
                if(varType.compareTo("float") == 0 && expressionType.compareTo("double") != 0)
                {
                   addVariable(varName, varType, getStringValueOf(Expression));                                                
                }

                else
                {
                   if(varType.compareTo("int") == 0 && expressionType.compareTo("float") != 0 && expressionType.compareTo("double") != 0)
                   {
                      addVariable(varName, varType, getStringValueOf(Expression));
                   }

                   else
                   {
                      if(varType.compareTo("char") == 0 && expressionType.compareTo("int") != 0 && expressionType.compareTo("float") != 0 && expressionType.compareTo("double") != 0)
                      {
                         addVariable(varName, varType, getStringValueOf(Expression));
                      }

                      else
                      {
                         if(varType.compareTo("boolean") == 0 && expressionType.compareTo("boolean") == 0)
                         {
                            addVariable(varName, varType, getStringValueOf(Expression));
                         }

                         else
                         {
                            if(varType.compareTo("boolean") == 0 && expressionType.compareTo("boolean") != 0)
                            {
                               errcode = 5;
                               System.out.println("\nIncompatible type for declaration. Cannot convert " + expressionType + " to " + varType);
                            }

                            else
                            {
                               errcode = 5;
                               System.out.println("\nIncompatible type for declaration. Explicit cast required to convert " + expressionType + " to " + varType);
                            }
                         }
                      }
                   }
                }
             }

             return errcode;
      }

      public int directAssignmentResult(String varType, String castType, String expressionType, String varName, String Expression)
      {
             int errcode = 0;

             if(varType.compareTo("double") == 0  && expressionType.compareTo("boolean") != 0)
             {
                if(expressionType.compareTo("char") == 0)
                {
                   addVariable(varName, varType, new Double((double) Expression.charAt(1)).toString());
                }

                else
                {
                   addVariable(varName, varType, new Double(Expression).toString());
                } 
             }

             else
             {
                if(varType.compareTo("float") == 0 && expressionType.compareTo("double") != 0  && expressionType.compareTo("boolean") != 0)
                {
                   if(expressionType.compareTo("char") == 0)
                   {
                      addVariable(varName, varType, new Float((float) Expression.charAt(1)).toString());
                   }
 
                   else
                   {
                      addVariable(varName, varType, new Float(Expression).toString());
                   } 
                }

                else
                {
                   if(varType.compareTo("int") == 0 && expressionType.compareTo("float") != 0 && expressionType.compareTo("double") != 0 && expressionType.compareTo("boolean") != 0)
                   {
                      if(expressionType.compareTo("char") == 0)
                      {
                         addVariable(varName, varType, new Integer((int) Expression.charAt(1)).toString());
                      }

                      else
                      {
                         addVariable(varName, varType, new Integer(Expression).toString());
                      } 
                   }

                   else
                   {
                      if(varType.compareTo("char") == 0 && expressionType.compareTo("int") != 0 && expressionType.compareTo("float") != 0 && expressionType.compareTo("double") != 0 && expressionType.compareTo("boolean") != 0)
                      {
                         addVariable(varName, varType, new StringBuffer("").append(Expression.charAt(1)).toString());
                      }

                      else
                      {
                         if(varType.compareTo("boolean") == 0 && expressionType.compareTo("boolean") == 0)
                         {
                            addVariable(varName, varType, new Boolean(Expression).toString());
                         }

                         else
                         {
                            if(varType.compareTo("boolean") == 0 && expressionType.compareTo("boolean") != 0)
                            {
                               errcode = 5;
                               System.out.println("\nIncompatible type for declaration. Cannot convert " + expressionType + " to " + varType);
                            }

                            else
                            {
                               if(varType.compareTo("boolean") != 0 && expressionType.compareTo("boolean") == 0)
                               {
                                  errcode = 5;
                                  System.out.println("\nIncompatible type for declaration. Cannot convert " + expressionType + " to " + varType);
                               }

                               else
                               {
                                  errcode = 5;
                                  System.out.println("\nIncompatible type for declaration. Explicit cast required to convert " + expressionType + " to " + varType);
                               }
                            }
                         }
                      }
                   }
                }
             }

             return errcode;
      }

      public int explicitCastResult(String varType, String castType, String expressionType, String varName, String Expression)
      {
             int errcode = 0;

             if(varType.compareTo("double") == 0)
             {
                if(castType.compareTo("int") == 0 && expressionType.compareTo("char") == 0)
                {
                   addVariable(varName, varType, new Double(new Integer((int) Expression.charAt(1)).toString()).toString());
                }
                                                     
                else
                {
                   if(castType.compareTo("int") == 0 && (expressionType.compareTo("double") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("int") == 0))
                   {
                      addVariable(varName, varType, new Double((int) new Double(Expression).doubleValue()).toString());
                   }

                   else
                   {
                      if(castType.compareTo("float") == 0 && expressionType.compareTo("char") == 0)
                      {
                         addVariable(varName, varType, new Double(new Float((float) Expression.charAt(1)).toString()).toString());
                      }

                      else
                      {
                         if(castType.compareTo("float") == 0 && (expressionType.compareTo("double") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("int") == 0))
                         {
                            addVariable(varName, varType, new Double(new Float(Expression).toString()).toString());
                         }

                         else
                         {
                            if(castType.compareTo("char") == 0 && expressionType.compareTo("char") == 0)
                            {
                               addVariable(varName, varType, new Double((double) Expression.charAt(1)).toString());
                            }

                            else
                            {
                               if(castType.compareTo("char") == 0 && (expressionType.compareTo("double") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("int") == 0))
                               {
                                  addVariable(varName, varType, new Double((int) new Double(Expression).doubleValue()).toString());
                               }

                               else
                               {
                                  if(castType.compareTo("double") == 0 && expressionType.compareTo("char") == 0)
                                  {
                                     addVariable(varName, varType, new Double((double) Expression.charAt(1)).toString());
                                  }

                                  else
                                  {
                                     if(castType.compareTo("double") == 0 && (expressionType.compareTo("double") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("int") == 0))
                                     {
                                        addVariable(varName, varType, new Double(Expression).toString());
                                     }
  
                                     else
                                     {
                                        errcode = 5;
                                        System.out.println("\nIncompatible type for declaration. Explicit cast required to convert " + castType + " to " + varType);
                                     }
                                  }
                               }
                            }
                         }
                      }
                   }
                }
             }

             else
             {
                if(varType.compareTo("float") == 0)
                {
                   if(castType.compareTo("int") == 0 && expressionType.compareTo("char") == 0)
                   {
                      addVariable(varName, varType, new Float(new Integer ((int) Expression.charAt(1)).toString()).toString());
                   }

                   else
                   {
                      if(castType.compareTo("int") == 0 && (expressionType.compareTo("int") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("double") == 0))
                      {
                         addVariable(varName, varType, new Float((int) new Double(Expression).doubleValue()).toString());
                      }

                      else
                      {
                         if(castType.compareTo("char") == 0 && expressionType.compareTo("char") == 0)
                         {
                            addVariable(varName, varType, new Float(new Integer((int) Expression.charAt(1)).toString()).toString());
                         }

                         else
                         {
                            if(castType.compareTo("char") == 0 && (expressionType.compareTo("int") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("double") == 0))
                            {
                               addVariable(varName, varType, new Float((int) new Float(Expression).floatValue()).toString());
                            }

                            else
                            {
                               if(castType.compareTo("float") == 0 && expressionType.compareTo("char") == 0)
                               {
                                  addVariable(varName, varType, new Float((float) Expression.charAt(1)).toString());
                               }

                               else
                               {
                                  if(castType.compareTo("float") == 0 && (expressionType.compareTo("int") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("double") == 0))
                                  {
                                     addVariable(varName, varType, new Float(Expression).toString());
                                  }
   
                                  else
                                  {
                                     errcode = 5;
                                     System.out.println("\nIncompatible type for declaration. Explicit cast required to convert " + castType + " to " + varType);
                                  }
                               }
                            }
                         }
                      }
                   }
                }
                                                     
                else
                {
                   if(varType.compareTo("int") == 0)
                   {
                      if(castType.compareTo("int") == 0 && expressionType.compareTo("char") == 0)
                      {
                         addVariable(varName, varType, new Integer((int) Expression.charAt(1)).toString());
                      }

                      else
                      {
                         if(castType.compareTo("int") == 0 && (expressionType.compareTo("double") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("int") == 0))
                         {
                            addVariable(varName, varType, new Integer((int) new Double(Expression).doubleValue()).toString());
                         }

                         else
                         {
                            if(castType.compareTo("char") == 0 && expressionType.compareTo("char") == 0)
                            {
                               addVariable(varName, varType, new Integer((int) Expression.charAt(1)).toString());
                            }

                            else
                            {
                               if(castType.compareTo("char") == 0 && (expressionType.compareTo("double") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("int") == 0))
                               {
                                  addVariable(varName, varType, new Integer((int) new Double(Expression).doubleValue()).toString());
                               }

                               else
                               {
                                  errcode = 5;
                                  System.out.println("\nIncompatible type for declaration. Explicit cast required to convert " + castType + " to " + varType);
                               }
                            }
                         }
                      }
                   }

                   else
                   {
                      if(varType.compareTo("char") == 0)
                      {
                         if(castType.compareTo("char") == 0 && expressionType.compareTo("char") == 0)
                         {
                            addVariable(varName, varType, new StringBuffer("").append(Expression.charAt(1)).toString());
                         }

                         else
                         {
                            if(castType.compareTo("char") == 0 && (expressionType.compareTo("int") == 0 || expressionType.compareTo("float") == 0 || expressionType.compareTo("double") == 0))
                            {
                               addVariable(varName, varType, new StringBuffer("").append((char) new Double(Expression).doubleValue()).toString());
                            }

                            else
                            {
                               errcode = 5;
                               System.out.println("\nIncompatible type for declaration. Explicit cast required to convert " + castType + " to " + varType);
                            }
                         }
                      }

                      else
                      {
                         if(varType.compareTo("boolean") == 0)
                         {
                            if(castType.compareTo("boolean") == 0 && expressionType.compareTo("boolean") == 0)
                            {
                               addVariable(varName, varType, new Boolean(Expression).toString());
                            }

                            else
                            {
                               errcode = 5;
                               System.out.println("\nIncompatible type for declaration. Cannot convert " + expressionType + " to " + varType);
                            }
                         }
                      }
                   }
                }         
             }

             return errcode;
      } 

      public int addNewVariable(StringTokenizer tokens, String KeyWord) throws MyException
      {
             String varType = KeyWord;
             int errcode = 0;
             int asciival = 0;
             boolean error = false;
             boolean done = false;
             String initVar = "";
             String Expression = "";
             String currentToken = "";
             String expressionType = "";
             String castType = "";

             if(!tokens.hasMoreTokens())
             {
                error = true;
                errcode = 1;
             }

             while(tokens.hasMoreTokens())
             {
                   initVar += tokens.nextToken();
             }

             initVar = initVar.trim();

             if(!initVar.endsWith(";"))
             {
                errcode = 1;
                error = true;
             }

             tokens = new StringTokenizer(initVar, "()=\r\\>\"; ,", true);

             while(!error && !done)
             {
                 if(tokens.hasMoreTokens() && !done && !error)
                 {
                    String varName = tokens.nextToken().trim();

                    while(varName.length() == 0)
                    {
                          varName = tokens.nextToken().trim();
                    }

                    if(varName.compareTo(";") == 0)
                    {
                       errcode = 5;
                       error = true;
                       System.out.println("\nNo identifier specified");
                    }

                    else
                    {
                       if(varName.compareTo(",") == 0)
                       {
                          varName = tokens.nextToken().trim();

                          while(varName.length() == 0)
                          {
                                varName = tokens.nextToken().trim();
                          }
                       }

                       if(varName.compareTo(";") == 0)
                       {
                          errcode = 5;
                          error = true;
                          System.out.println("\nNo identifier specified");
                       }
                    }

                    asciival = varName.charAt(0);

                    if((asciival >= 48 && asciival <= 57) ||
                        varName.indexOf("~") >= 0 || 
                        varName.indexOf("`") >= 0 || 
                        varName.indexOf("!") >= 0 ||
                        varName.indexOf("@") >= 0 ||
                        varName.indexOf("#") >= 0 ||
                        varName.indexOf("%") >= 0 || 
                        varName.indexOf("^") >= 0 || 
                        varName.indexOf("&") >= 0 || 
                        varName.indexOf("*") >= 0 || 
                        varName.indexOf("(") >= 0 || 
                        varName.indexOf(")") >= 0 || 
                        varName.indexOf("|") >= 0 || 
                        varName.indexOf("\\") >= 0 || 
                        varName.indexOf("/") >= 0 || 
                        varName.indexOf(".") >= 0 || 
                        varName.indexOf("?") >= 0 || 
                        varName.indexOf("-") >= 0 ||
                        varName.indexOf("`") >= 0 || 
                        varName.indexOf(";") >= 0 || 
                        varName.indexOf(":") >= 0 || 
                        varName.indexOf("\"") >= 0 || 
                        varName.indexOf("<") >= 0 || 
                        varName.indexOf(">") >= 0 ||
                        varName.indexOf("[") >= 0 || 
                        varName.indexOf("]") >= 0)
                    {
                       errcode = 5;
                       error = true;
                       System.out.println("\nInvalid identifier name");
                    }

                    if(varName.compareTo("char") == 0 || varName.compareTo("int") == 0 || varName.compareTo("float") == 0 || varName.compareTo("double") == 0 || varName.compareTo("boolean") == 0)
                    {
                       errcode = 5;
                       error = true;
                       System.out.println("\nInvalid identifier name. " + varName + " is a reserved word");
                    }

                    if(varName.compareTo("=") == 0)
                    {
                       errcode = 5;
                       error = true;
                       System.out.println("\nIdentifier missing");
                    }
                    
                    if(doesVariableExist(varName) || error)
                    {
                       errcode = 5;
                       if(!error)
                       {
                          error = true;
                          System.out.println("\nVariable " + varName + " has already been defined");
                       }
                    }   

                    else
                    {
                       currentToken = tokens.nextToken().trim();

                       while(currentToken.length() == 0)
                       {
                          currentToken = tokens.nextToken().trim();
                       }

                       if(currentToken.compareTo(";") == 0)
                       {
                          addVariable(varName, varType, null);
                          done = true;
                       }

                       else
                       {
                          if(currentToken.compareTo(",") == 0)
                          {
                             addVariable(varName, varType, null); 
                          }

                          else
                          {
                             if(currentToken.compareTo("=") != 0)
                             {
                                errcode = 5;
                                error = true;
                                System.out.println("\nAssignment symbol \'=\' missing");
                             }

                             else
                             {
                                if(currentToken.compareTo(";") == 0)
                                {
                                   errcode = 5;
                                   error = true;
                                   System.out.println("\nRvalue required");
                                }

                                else
                                {
                                   Expression = tokens.nextToken().trim();

                                   if(Expression.compareTo(";") == 0)
                                   {
                                      errcode = 5;
                                      error = true;
                                      System.out.println("\nRValue required");
                                   }

                                   else
                                   {
                                      while(Expression.length() == 0)
                                      {
                                         Expression = tokens.nextToken().trim();
                                      }

                                      if(Expression.compareTo("(") == 0)
                                      {
                                         castType = tokens.nextToken().trim();

                                         while(castType.length() == 0)
                                         {
                                            castType = tokens.nextToken().trim();
                                         }

                                         Expression =  tokens.nextToken().trim();

                                         while(Expression.length() == 0)
                                         {
                                            Expression = tokens.nextToken().trim();
                                         }

                                         if(Expression.compareTo(")") != 0)
                                         {
                                            errcode = 5;
                                            error = true;
                                            System.out.println("\n) missing");
                                         }

                                         else
                                         {
                                            Expression = tokens.nextToken().trim();

                                            while(Expression.length() == 0)
                                            {
                                               Expression = tokens.nextToken().trim();
                                            }

                                            expressionType = typeOf(Expression);

                                            if(expressionType.compareTo("Identifier") == 0)
                                            {
                                               if(doesVariableExist(Expression))
                                               {
                                                  expressionType = getTypeOf(Expression);
                                                  Expression = getStringValueOf(Expression);
                                               }

                                               else
                                               {
                                                  errcode = 5;
                                                  error = true;
                                                  System.out.println("\nAttempt to reference " + Expression + ", an undefined symbol");
                                               }                                               
                                           }

                                           if(!error)
                                           {
                                               if(expressionType.compareTo("invalid") == 0)
                                               {
                                                  errcode = 5;
                                                  error = true;
                                                  System.out.println("\nInvalid RValue expression");
                                               }

                                               else
                                               {
                                                  errcode = explicitCastResult(varType, castType, expressionType, varName, Expression);
                                                  error = (errcode != 0);
                                               }
                                            }
                                         }                                         
                                      }
                                      
                                      else
                                      {
                                         expressionType = typeOf(Expression);

                                         if(expressionType.compareTo("Identifier") != 0)
                                         {
                                            if(expressionType.compareTo("invalid") == 0)
                                            {
                                               errcode = 5;
                                               error = true;
                                               System.out.println("\nInvalid RValue expression");
                                            }

                                            else
                                            {
                                               errcode = directAssignmentResult(varType, castType, expressionType, varName, Expression);
                                               error = (errcode != 0);
                                            }
                                         }

                                         else
                                         {
                                            if(doesVariableExist(Expression))
                                            {
                                               expressionType = getTypeOf(Expression);

                                               errcode = directAssignmentFromVarResult(varType, castType, expressionType, varName, Expression);
                                               error = (errcode != 0);
                                            }

                                            else
                                            {
                                               errcode = 5;
                                               error = true;
                                               System.out.println("\nAttempt to reference " + Expression + ", an undefined symbol");
                                            }
                                         }
                                      }
                                   }
                                }
                             }
                          }
                       }
                    }
                 }

                 if(!error && tokens.countTokens() <= 1)
                 {
                    done = true;
                 }
             }

             return errcode;
      }

      public String typeOf(String varValue)
      {
             String varType = "invalid";
             int varIntValue = 0;
             double varFloatingPointValue = 0;
             boolean isIdentifier = false;
	     boolean done = false;
             int indexOfDecimal = 0;

             if(varValue.indexOf("~") >= 0 || 
                varValue.indexOf("`") >= 0 || 
                varValue.indexOf("!") >= 0 ||
                varValue.indexOf("@") >= 0 ||
                varValue.indexOf("#") >= 0 ||
                varValue.indexOf("%") >= 0 || 
                varValue.indexOf("^") >= 0 || 
                varValue.indexOf("&") >= 0 || 
                varValue.indexOf("*") >= 0 || 
                varValue.indexOf("(") >= 0 || 
                varValue.indexOf(")") >= 0 || 
                varValue.indexOf("|") >= 0 || 
                varValue.indexOf("\\") >= 0 || 
                varValue.indexOf("/") >= 0 || 
                varValue.indexOf("?") >= 0 || 
                (varValue.indexOf("-") >= 0 && varValue.length() == 1) ||
		(varValue.indexOf(".") >= 0 && varValue.length() == 1) ||
                varValue.indexOf("`") >= 0 || 
                varValue.indexOf(";") >= 0 || 
                varValue.indexOf(":") >= 0 || 
                varValue.indexOf("\"") >= 0 || 
                varValue.indexOf("<") >= 0 || 
                varValue.indexOf(">") >= 0 ||
                varValue.indexOf("[") >= 0 || 
                varValue.indexOf("]") >= 0)
             {
	        done = true; //warning: a legitimate - appears as invalid

             }
             if(!done && varValue.compareTo("true") == 0 || varValue.compareTo("false") == 0)
             {
                varType = "boolean";
             }

             else
             {
                if(!done &&
		   ((int) varValue.charAt(0) != 39 && ((int) varValue.charAt(0) < 48  || (int) varValue.charAt(0) > 57)) &&
                   !((int) varValue.charAt(0) == 45 && (int) varValue.charAt(1) >= 48 && (int) varValue.charAt(1) <= 57))
                {
                   varType = "Identifier";                
                   isIdentifier = true;
                }

                else
                {
                   if(!done && (int) varValue.charAt(0) == 39 &&  varValue.length() == 3)
                   {
                      varType = "char";
                   }

                   else
                   {
		      if(!done)
		      {
                         try
                         {
                           varIntValue = Integer.parseInt(varValue);
                           varType = "int";
                         }

                         catch(Exception Fatal)
                         {
                               try
                               {
                                 varFloatingPointValue = new Double(varValue).doubleValue();
                
                                 indexOfDecimal = varValue.indexOf(".");

                                 if(varValue.substring(indexOfDecimal + 1, varValue.length()).length() > 4)
                                 {
                                    varType = "double";
                                 }

                                 else
                                 {
                                    varType = "float";
                                 }
                               }

                               catch(Exception MoreFatal)
                               {
                                     varType = "invalid";
                               }
                         }
		     }
                  }
               }
            }

            return varType;
      }

      public int getIntValueOf(String varName) throws MyException
      {
             int intValue = 0;

             intValue = Integer.parseInt(getVariable(varName));

             return intValue;
      }

      public double getDoubleValueOf(String varName) throws MyException
      {
             double doubleValue = 0;

             doubleValue = new Double(getVariable(varName)).doubleValue();

             return doubleValue;
      }
             
      public float getFloatValueOf(String varName) throws MyException
      {
             float floatValue = 0;

             floatValue = new Float(getVariable(varName)).floatValue();

             return floatValue;
      }

      public String getStringValueOf(String varName) throws MyException
      {
             String stringValue = "";

             stringValue = getVariable(varName);

             return stringValue;
      }

      public char getCharValue(String varName) throws MyException
      {
             char charValue = getVariable(varName).charAt(0);

             return charValue;
      }

      public void display()
      {
             System.out.println("These are the variables currently in the symbol table:");
             System.out.println("Type\tName\tValue");
             Variable thisVariable = new Variable();

             int i = 0;

             for(i = 0; i < variableCount; i++)
             {
                 thisVariable = (Variable) variableList.elementAt(i);

                 thisVariable.display();
             }
      }             

      public String getTypeOf(String varName) throws MyException
      {
             String varType = "";
             int i = 0;
             boolean found = false;
             Variable thisVariable = new Variable();

             while(i < variableCount && !found)
             {
                   thisVariable = (Variable) variableList.elementAt(i);

                   if(thisVariable.returnName().compareTo(varName) == 0)
                   {
                      varType = thisVariable.returnType();
                      found = true;
                   }
 
                   i++;
             }

             if(found)
             {
                return varType;
             }

             else
             {
                 throw UndefinedVariableException;
             }
      }

      public String getVariable(String varName) throws MyException
      {
             String varValue = "";
             int i = 0;
             boolean found = false;
             Variable thisVariable = new Variable();

             while(i < variableCount && !found)
             {
                   thisVariable = (Variable) variableList.elementAt(i);

                   if(thisVariable.returnName().compareTo(varName) == 0)
                   {
                      varValue = thisVariable.returnValue();
                      found = true;
                   }
 
                   i++;
             }

             if(found)
             {
                return varValue;
             }

             else
             {
                 throw UndefinedVariableException;
             }
     }

     public boolean doesVariableExist(String varName)
     {
            int i = 0;
            boolean found = false;
            Variable thisVariable = new Variable();

            while(i < variableCount && !found)
            {
                  thisVariable = (Variable) variableList.elementAt(i);

                  if(thisVariable.returnName().compareTo(varName) == 0)
                  {
                     found = true;
                  }
 
                  i++;
            }

            return found;            
    }

    public void setType(String varName, String varType) throws MyException
    {
           int i = 0;
           boolean found = false;
           Variable thisVariable = new Variable();
     
           while(i < variableCount && !found)
           {
                 thisVariable = (Variable) variableList.elementAt(i);

                 if(thisVariable.returnName().compareTo(varName) == 0)
                 {
                    found = true;
                 }
 
                 i++;
           }

           if(found)
           {
              thisVariable.setType(varType);
           }

           else
           {
              throw UndefinedVariableException;
           }
    }

    public int setNewValue(String varName, String varType, String varValue) throws MyException
    {
           int i = 0;
           boolean found = false;
           boolean varIsNull = false;
           Variable thisVariable = new Variable();
           int storeResult = 0;

           while(i < variableCount && !found)
           {
                 thisVariable = (Variable) variableList.elementAt(i);

                 if(thisVariable.returnName().compareTo(varName) == 0)
                 {
                    found = true;
                 }
 
                 i++;
           }

           if(found)
           {
              if(varValue.compareTo("null") == 0)
              {
                 storeResult = thisVariable.setValueAsString(varValue);
                 varIsNull = true;
              }

              if(varType.compareTo("int") == 0 && !varIsNull)
              {
                 storeResult = thisVariable.setValueAsInt(new Integer(varValue).intValue());
              }

              else
              {
                 if(varType.compareTo("double") == 0 && !varIsNull)
                 {
                    storeResult = thisVariable.setValueAsDouble(new Double(varValue).doubleValue());
                 }

                 else
                 {
                    if(varType.compareTo("float") == 0 && !varIsNull)
                    {
                       storeResult = thisVariable.setValueAsFloat(new Float(varValue).floatValue());
                    }

                    else
                    {
                       if(varType.compareTo("String") == 0 && !varIsNull)
                       {
                          storeResult = thisVariable.setValueAsString(varValue);
                       }

                       else
                       {
                          if(varType.compareTo("char") == 0 && !varIsNull)
                          {
                             storeResult = thisVariable.setValueAsChar(varValue.charAt(0));
                          }                          
                       }
                    }
                 }
              }
           }

           else
           {
              throw UndefinedVariableException;
           }

           return storeResult;
    }
}
